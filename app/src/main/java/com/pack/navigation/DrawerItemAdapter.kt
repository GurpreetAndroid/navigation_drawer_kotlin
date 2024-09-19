package com.infodart.kenstar_crm

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pack.navigation.R


class DrawerItemAdapter(private var items: ArrayList<NavigationItemModel>, private var currentPos: Int) :
    RecyclerView.Adapter<DrawerItemAdapter.NavigationItemViewHolder>() {

    private lateinit var context: Context

    class NavigationItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var navigationTitle: TextView
        var navigationIcon: ImageView

        init {
            navigationTitle = itemView.findViewById(R.id.navigation_title)
            navigationIcon = itemView.findViewById(R.id.navigation_icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationItemViewHolder {
        context = parent.context
        val navItem = LayoutInflater.from(parent.context).inflate(R.layout.nav_drawer_item, parent, false)
        return NavigationItemViewHolder(navItem)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: NavigationItemViewHolder, position: Int) {
        if (position == currentPos) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.gray))
            holder.navigationIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
            holder.navigationTitle.setTextColor(Color.WHITE)
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            holder.navigationIcon.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)
            holder.navigationTitle.setTextColor(Color.BLACK)
        }
        holder.navigationTitle.text = items[position].title
        holder.navigationIcon.setImageResource(items[position].icon)

    }
}