package com.example.there_android

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.there_android.databinding.ActivityAddhistoryBinding
import java.io.File
import java.util.jar.Manifest

class AddHistoryActivity: AppCompatActivity() {

    lateinit var binding: ActivityAddhistoryBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_post)
        binding = ActivityAddhistoryBinding.inflate(layoutInflater)

        //X 표시 누르면 Post 화면으로 전환
        binding.addhistoryCloseIv.setOnClickListener {
            val builder =  AlertDialog.Builder(this)
                .setMessage("해당 페이지를 떠나시겠습니까? 작성 페이지를 떠나면 작성 중인 글이 저장되지 않습니다.")
                .setPositiveButton("확인",
                DialogInterface.OnClickListener { dialog, which ->
                    startActivity(intent)
                    finish()
                })
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, which ->
                        startActivity(intent)
                    })
            builder.show()
        }

        //'완료' 버튼 누르면 서버로 데이터 전송 & Post 화면으로 전환
        binding.addhistoryDoneTv.setOnClickListener {
            addHistory()

        }

        //사진 첨부
        binding.addhistoryAddimgCl.setOnClickListener {
            binding.addhistoryAddimgCl.visibility = View.INVISIBLE
            selectGallery()
        }

    }
    //제목에 글씨가 입력되는 체크 표시
    private fun checkTitle(){

    }

    companion object{
        const val REVIEW_MIN_LENGTH = 10
        //갤러리 권한 요청
        const val REQ_GALLERY = 1

        //API 호출시 Parameter key,값
        const val PARAM_KEY_IMAGE = "image"
        const val PARAM_KEY_PRODUCT_ID = "product_id"
        const val PARAM_KEY_REVIEW = "review_content"
        const val PARAM_KEY_RATING = "rating"
    }

    //이미지를 결과값으로 받는 변수
    private val imageResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        result->
        if(result.resultCode == RESULT_OK){
            //이미지를 받으면 ImageView에 적용
            val imageUri = result.data?.data
            imageUri?.let{
                //서버 업로드를 위해 파일 형태로 변환
                val imageFile = File(getRealPathFromURI(it))

                //이미지 불러오기
                Glide.with(this)
                    .load(imageUri)
            }
        }
    }

    fun getRealPathFromURI(uri: Uri): String {
        val buildName = Build.MANUFACTURER
        if(buildName.equals("Xiaomi")){
            return uri.path!!
        }
        var columnIndex = 0
        var proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(uri, proj, null, null, null)
        if(cursor!!.moveToFirst()){
            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        }
        val result = cursor.getString(columnIndex)
        cursor.close()
        return result
    }

    //갤러리를 부르는 매서드
        private fun selectGallery(){
        val writePermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val readPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)

        //권한 확인
        if(writePermission == PackageManager.PERMISSION_DENIED || readPermission == PackageManager.PERMISSION_DENIED){
            //권한 요청
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE), REQ_GALLERY)
        }
        else{
            //권한 있는 경우 갤러리 실행
            val intent = Intent(Intent.ACTION_PICK)
            //intent의 data와 type을 동시에 설정하는 매서드
            intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            imageResult.launch(intent)
        }
    }

    //Request로 보낼 내용 얻는 함수
    private fun getContent() : AddHistoryRequest {
        val title : String = binding.addhistoryAddtitleEt.text.toString()
        val content : String = binding.addhistoryAddcontentEt.text.toString()
        return AddHistoryRequest()
    }

    //History 정보를 서버에 전달
    private fun addHistory(){
        val addHistoryService = AddHistoryService()
        addHistoryService.setAddHistoryView(this)
        addHistoryService.addHistory(getContent())
    }

    override fun onAddHistorySuccess(code: Int, message : String){
        //새로운 history 생성
        finish()
        Toast.makeText(this, "history가 생성되었습니다", Toast.LENGTH_SHORT).show()
    }

}