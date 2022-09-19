package com.example.there_android.MyPage

interface MyPageView {
    fun onMyPageSuccess(result : MyPageResult)
    fun onMyPageFailure (code: Int, message: String)
}

interface FollowView {
    fun onFollowSuccess(result : List<FollowerList>)
    fun onFollowFailure (code: Int, message: String)
}

interface NoteView {
    fun onNoteSuccess(result : NoteResult)
    fun onNoteFailure (code: Int, message: String)

    fun onPostNoteSuccess(result : String)
    fun onPostNoteFailure (code: Int, message: String)
}