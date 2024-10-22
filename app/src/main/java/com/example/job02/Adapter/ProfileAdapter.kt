package com.example.jon02.Adapter



import android.provider.ContactsContract.Profile
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.job02.Models.UserProfile
import com.example.job02.R

class ProfileAdapter():ListAdapter<UserProfile, ProfileAdapter.ProfileViewHolder>(DiffCallback()) {
    private lateinit var  OnItemClickListener:((UserProfile)->Unit)
    private lateinit var  OnDeleteClickListener:((UserProfile)->Unit)
    private lateinit var  OnUpdateClickListener:((UserProfile)->Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.profile_list_layout, parent, false)
        return ProfileViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    fun setOnItemClickListener(listener: (UserProfile) -> Unit) {

        OnItemClickListener = listener
    }
    fun setOnDeleteClickListener(listener: (UserProfile) -> Unit) {
        OnDeleteClickListener = listener
    }
    fun setOnUpdateClickListener(listener: (UserProfile) -> Unit) {
        OnUpdateClickListener = listener
    }

    inner class ProfileViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

        private val profileName: TextView = itemView.findViewById(R.id.userNameTxt)
        private val profileEmail: TextView = itemView.findViewById(R.id.userEmailTxt)
        private val profileDob: TextView = itemView.findViewById(R.id.DobTxt)
        private val profileDistrict: TextView = itemView.findViewById(R.id.DistrictTxt)
        private val profileMobile: TextView = itemView.findViewById(R.id.MobileTxt)
        private val updateBtn: ImageButton = itemView.findViewById(R.id.updateBtn)
        private val deleteBtn: ImageButton = itemView.findViewById(R.id.deleteBtn)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val profile = getItem(position)
                    OnItemClickListener?.invoke(profile)
                }
            }
            deleteBtn.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val profile = getItem(position)
                    OnDeleteClickListener?.invoke(profile)
                }
            }
            updateBtn.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val profile = getItem(position)
                    OnUpdateClickListener?.invoke(profile)
                }
            }

        }

        fun bind(userProfile: UserProfile) {
            profileName.text = userProfile.name
            profileEmail.text = userProfile.email
            profileDob.text = userProfile.dob
            profileDistrict.text = userProfile.district
            profileMobile.text = userProfile.mobile

        }

    }
}
class  DiffCallback :DiffUtil.ItemCallback<UserProfile>(){
    override fun areItemsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean {
        return  oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean {
        return  oldItem == newItem

    }

}


