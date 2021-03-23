package id.buaja.post.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.buaja.domain.model.Post
import id.buaja.post.databinding.ItemListPostBinding
import id.buaja.post.utils.toHtml

/**
 * Created by Julsapargi Nursam on 3/24/21.
 */


class HomeAdapter(private val data: List<Post>, private val listener: (Post) -> Unit) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemListPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position], listener)

    class ViewHolder(private val itemListPostBinding: ItemListPostBinding) : RecyclerView.ViewHolder(itemListPostBinding.root) {
        fun bind(item: Post, listener: (Post) -> Unit) {
            with(itemListPostBinding) {
                tvTitle.text = item.title
                tvBody.text = item.body.toHtml()
            }
        }
    }
}