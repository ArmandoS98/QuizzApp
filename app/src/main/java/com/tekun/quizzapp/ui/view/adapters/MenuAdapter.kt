package com.tekun.quizzapp.ui.view.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tekun.quizzapp.R
import com.tekun.quizzapp.databinding.ItemMenuBinding
import com.tekun.quizzapp.data.model.QuizzesModel

class MenuAdapter(val context: Context?) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    private var menuList: List<QuizzesModel> = listOf()

    fun setMenu(movieList: List<QuizzesModel>) {
        this.menuList = movieList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Image
        /*  holder.itemView.product_image.loadByResource(menuList[position].res)
          holder.itemView.tv_identificador.text = menuList[position].item*/

        holder.binding.btnPlay.setOnClickListener {
            //Send Other View
            val builder = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setEnterAnim(R.anim.from_left)
                .setExitAnim(R.anim.to_right)
                .setPopEnterAnim(R.anim.from_right)
                .setPopExitAnim(R.anim.to_left)

            //Envio de información
            val bundle = Bundle()
            bundle.putString(context!!.getString(R.string.key_bundle), menuList[position].urlQuiz)
            val opciones: NavOptions = builder.build()
            Navigation.findNavController(it).navigate(R.id.nav_quiz, bundle, opciones)
        }
    }

    override fun getItemCount() = menuList.size

    inner class ViewHolder(val binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root)

}
