//package com.example.there_android
//
//import android.R
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.there_android.databinding.ItemChatlistBinding
//
//class ChatAdapter (): RecyclerView.Adapter<ChatAdapter.ChatViewHolder>(){
//    var chatlist = mutableListOf<ChatData>()
//
//    inner class ChatViewHolder(private val binding: ItemChatlistBinding): RecyclerView.ViewHolder(binding.root) {
//        fun bind(chatData: ChatData){
////            binding.chatNameTv.text=chatData.nickname
//            binding.chatTimeTv.text= chatData.time.toString()
//            binding.chatTextTv.text=chatData.text
//            binding.chatCountTv.text= chatData.count.toString()
//        }
//    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val binding =
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        holder.bind(chatlist[position])
//    }
//
//    override fun getItemCount(): Int = chatlist.size
//}
////Adapter : listview를 만들어서 recyclerview와 연결해줌 , Holder에서 만들어 준 listView를 inflater를 이용해 객체화 시키고 실제 데이터를 담아줌
////즉, Holder가 listview 그릇을 만들면 Adapter가 실제 데이터를 담은 listView를 만들어주는 것
////class ChatAdapter(items: List<Chat>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
////    // 해당 어댑터의 ViewHolder를 상속받는다.
////    private val items: List<Chat>
////    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
////        // ViewHodler 객체를 생성 후 리턴한다.
////        return ChatViewHolder(
////            LayoutInflater.from(parent.context).inflate(R.layout.item_Chat, parent, false)
////        )
////    }
////
////    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
////        // ViewHolder 가 재활용 될 때 사용되는 메소드
////        val data: Chat = items[position]
////        holder.title.setText(data.getTitle())
////        holder.contents.setText(data.getContents())
////    }
////
////    override fun getItemCount(): Int {
////        return items.size // 전체 데이터의 개수 조회
////    }
////
////    // 아이템 뷰를 저장하는 클래스
////    inner class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
////        // ViewHolder 에 필요한 데이터들을 적음.
////        private val title: TextView
////        private val contents: TextView
////
////        init {
////            // 아이템 뷰에 필요한 View
////            title = itemView.findViewById(R.id.item_Chat_title)
////            contents = itemView.findViewById(R.id.item_Chat_content)
////        }
////    }
////
////    init {
////        this.items = items
////    }
////}