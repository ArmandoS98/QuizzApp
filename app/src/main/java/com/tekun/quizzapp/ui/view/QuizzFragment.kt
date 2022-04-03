package com.tekun.quizzapp.ui.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.tekun.quizzapp.R
import com.tekun.quizzapp.data.sharedpreferences.PreferencesKey
import com.tekun.quizzapp.data.sharedpreferences.PreferencesProvider
import com.tekun.quizzapp.databinding.FragmentQuizzBinding
import com.tekun.quizzapp.domain.RankingItem
import com.tekun.quizzapp.ui.extensions.loadByInternet
import com.tekun.quizzapp.ui.viewmodel.QuizViewModel
import com.tekun.quizzapp.ui.viewmodel.RankingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizzFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentQuizzBinding? = null
    private val binding get() = _binding!!
    private val quizViewModel: QuizViewModel by viewModels()
    private val rankingViewModel: RankingViewModel by viewModels()
    private var countDownTimer: CountDownTimer? = null
    private var countDownTimerNextQuestion: CountDownTimer? = null
    private var isActive = false
    private var score = 0
    private var question = 1
    private val limit = 10
    private var correctAnswer: Int = 0
    var mp: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuizzBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isActive = true
        val turnMusic = PreferencesProvider.bool(requireContext(), PreferencesKey.TURNSOUND)

        if (turnMusic) {
            mp = MediaPlayer.create(requireContext(), R.raw.audio)
            mp?.isLooping = true
            val levelMusic = PreferencesProvider.int(requireContext(), PreferencesKey.LEVEL_VOLUME)
            val level = (levelMusic?.div(100))?.toFloat()
            level?.let { mp?.setVolume(it, it) }
            mp?.start()
        }

        getRandomQuestion()

        //Opcions
        binding.btnOpcion1.setOnClickListener(this)
        binding.btnOpcion2.setOnClickListener(this)
        binding.btnOpcion3.setOnClickListener(this)
        binding.btnOpcion4.setOnClickListener(this)
        binding.btnNextQuestion.setOnClickListener(this)

    }


    private fun getRandomQuestion() {
        val optionsSelectable = listOf(
            binding.btnOpcion1,
            binding.btnOpcion2,
            binding.btnOpcion3,
            binding.btnOpcion4
        )

        quizViewModel.onCreate()

        quizViewModel.quizModel.observe(viewLifecycleOwner) { currentQuestion ->
            binding.textView.text = currentQuestion.question
            binding.imgHelp.loadByInternet(currentQuestion.imageUrl)
            correctAnswer = currentQuestion.answer
            val options = currentQuestion.options.split("|")
            options.forEachIndexed { pos, desc ->
                optionsSelectable[pos].text = desc
            }

            if (!isActive) {
                updateScore(score, question)
            } else {
                updateScore(score, question++)
                initCountDownTimer()
            }
        }
    }

    private fun resetButtons(textColor: Int) {
        val options = listOf(
            binding.btnOpcion1,
            binding.btnOpcion2,
            binding.btnOpcion3,
            binding.btnOpcion4
        )
        blockButtons(true)
        options.forEachIndexed { index, _ ->
            updateButtons(index, Color.TRANSPARENT, textColor)
        }
    }

    private fun updateScore(score: Int, question: Int) {
        binding.tvScore.text = "Score: $score"
        binding.tvQuestion.text = "Question: $question of $limit"
    }

    private fun initCountDownTimer() {
//        binding.countdown.visibility = View.VISIBLE

        if (countDownTimer != null) {
            countDownTimer!!.cancel()
        }

        countDownTimer = object : CountDownTimer(30000, 1) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvCountDownTimer.text = "${millisUntilFinished / 1000}s"
                val progress = (millisUntilFinished / 1).toInt()
                progressBarStatus(progress)

            }

            override fun onFinish() {
                binding.tvCountDownTimer.text = getString(R.string.done_time)
//                binding.countdown.visibility = View.GONE
                answerCorrect(-1)
            }
        }
        countDownTimer!!.start()
    }

    private fun progressBarStatus(progress: Int) {
        val max = binding.lpiCountDown.max
        val realProgress = max - progress
        println("Times: $max ; $realProgress")
        binding.lpiCountDown.progress = realProgress
    }

    private fun progressStyle(progressTintList: Int, progressBackgroundTintList: Int) {
        binding.progressBar2.progressTintList =
            ColorStateList.valueOf(ContextCompat.getColor(requireContext(), progressTintList))
        binding.progressBar2.progressBackgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                requireContext(),
                progressBackgroundTintList
            )
        )
    }

    private fun resetTimers() {
        countDownTimer?.cancel()
        countDownTimer = null
        countDownTimerNextQuestion?.cancel()
        countDownTimerNextQuestion = null
        isActive = false
    }

    private fun blockButtons(status: Boolean) {
        binding.btnOpcion1.isEnabled = status
        binding.btnOpcion2.isEnabled = status
        binding.btnOpcion3.isEnabled = status
        binding.btnOpcion4.isEnabled = status
        binding.btnNextQuestion.isEnabled = !status
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        mp!!.stop()
        resetTimers()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnOpcion1 -> {
                userSelected(1)
            }
            R.id.btnOpcion2 -> {
                userSelected(2)
            }
            R.id.btnOpcion3 -> {
                userSelected(3)
            }
            R.id.btnOpcion4 -> {
                userSelected(4)
            }
            R.id.btnNextQuestion -> {
                getNextQuestion()
            }
        }
    }

    private fun getNextQuestion() {
        //Cancelamos el countdown para la siguiente pregunta si esta activo
        if (countDownTimerNextQuestion != null) {
            countDownTimerNextQuestion?.cancel()
        }

        if (question <= limit) {
            resetButtons(ContextCompat.getColor(requireContext(), R.color.purple_500))
            quizViewModel.randomQuestion()
        } else {
            showResult()
        }
    }

    private fun showResult() {
        rankingViewModel.insertWinnerModel.observe(viewLifecycleOwner) {
            println("Result Insert: $it")

            findNavController().popBackStack(R.id.nav_quizzes, false)
        }
        dialogBienvenida(requireContext())
    }

    private fun userSelected(answer: Int) {
        countDownTimer?.cancel()
//        binding.countdown.visibility = View.GONE
        resetButtons(Color.GRAY)
        answerCorrect(answer)
    }

    private fun answerCorrect(answer: Int) {

        blockButtons(false)
        updateButtons(correctAnswer - 1, Color.GREEN, Color.BLACK)
        if (correctAnswer == answer) {
            score += 10
            updateScore(score, question - 1)
        } else
            if (answer != -1) {
                updateButtons(answer - 1, Color.RED, Color.WHITE)
            }

        //Redireccionando a la siguiente pregunta
        countDownTimerNextQuestion = object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                //Do nothing
            }

            override fun onFinish() {
                getNextQuestion()
            }
        }
        countDownTimerNextQuestion?.start()
    }

    private fun updateButtons(button: Int, background: Int, textColor: Int) {
        val options = listOf(
            binding.btnOpcion1,
            binding.btnOpcion2,
            binding.btnOpcion3,
            binding.btnOpcion4
        )

        options[button].setBackgroundColor(background)
        options[button].setTextColor(textColor)
    }

    private fun dialogBienvenida(context: Context) {
        var alertDialog1: AlertDialog? = null
        val dialogBuilder = AlertDialog.Builder(context)
        val layoutView: View =
            LayoutInflater.from(context).inflate(R.layout.custom_dialog_finish, null)
        val mButtonSi = layoutView.findViewById<MaterialButton>(R.id.btnSave)
        val scoreText = layoutView.findViewById<TextView>(R.id.tvScore)
        scoreText.text = "Score: $score"
        mButtonSi.setOnClickListener {
            val playerName =
                layoutView.findViewById<TextInputEditText>(R.id.tieNamePlayer).text.toString()
            if (playerName != "") {
                alertDialog1!!.dismiss()
                //last winner
                var lastWinner = PreferencesProvider.intWinner(
                    requireContext(),
                    PreferencesKey.LAST_WINNER
                )

                lastWinner = if (lastWinner == 9) 0 else (lastWinner!! + 1)

                //Saving the new id
                PreferencesProvider.set(requireContext(), PreferencesKey.LAST_WINNER, lastWinner)
                rankingViewModel.insertWinner(RankingItem(lastWinner, playerName, score))
            } else
                Toast.makeText(context, "Please enter a name", Toast.LENGTH_LONG).show()
        }
        dialogBuilder.setView(layoutView)
        alertDialog1 = dialogBuilder.create()
        alertDialog1.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog1.show()
    }
}