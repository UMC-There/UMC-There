package com.example.there_android.History

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.media.Image
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.esafirm.imagepicker.features.*
import com.example.there_android.R
import com.example.there_android.databinding.ActivityAddhistoryBinding
import org.json.JSONObject

class AddHistoryActivity: AppCompatActivity() , AddHistoryView {

    lateinit var binding: ActivityAddhistoryBinding

    private var images = listOf<com.esafirm.imagepicker.model.Image>()

    private var img = listOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        binding = ActivityAddhistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //X 표시 누르면 Post 화면으로 전환
        binding.addhistoryCloseIv.setOnClickListener {
            val builder =  AlertDialog.Builder(this)
                .setMessage("해당 페이지를 떠나시겠습니까? 작성 페이지를 떠나면 작성 중인 글이 저장되지 않습니다.")
                .setPositiveButton("확인",
                DialogInterface.OnClickListener { dialog, which ->
                    finish()
                })
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, which ->
                    })
            builder.show()
        }

        //'완료' 버튼 누르면 서버로 데이터 전송 & Post 화면으로 전환
        binding.addhistoryDoneTv.setOnClickListener {
            addHistory()
        }

        //제목 텍스트 작성 시 체크 표시
        val editText = binding.addhistoryAddtitleEt
        editText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if(p0?.length!! > 0){
                    binding.addhistoryTitleCheckIv.visibility = View.VISIBLE
                }
                else{
                    binding.addhistoryTitleCheckIv.visibility = View.INVISIBLE
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        //갤러리 접근 & 사진 첨부
        var launcher : ImagePickerLauncher = registerImagePicker { result ->
            if(result.isNotEmpty()){
                images = result
                img = images.map { it.toString() }
                Log.d("recyclerAdapter", "리사이클러뷰를 실행합니다.")
                setAdapter()
                Log.d("recyclerAdapter", "리사이클러뷰를 실행 완료.")
            }
        }

        binding.addhistoryAddimgCl.setOnClickListener {
            val config = ImagePickerConfig{
                mode = ImagePickerMode.MULTIPLE
                isFolderMode = false
                isIncludeVideo = false
                arrowColor = Color.WHITE
                imageTitle = "이미지를 선택하세요"
                isShowCamera = true
                returnMode = ReturnMode.NONE
            }

            Log.d("CHECK", images.toString())

            launcher.launch(config)

            binding.addhistoryBigImageRv.visibility = View.VISIBLE
            binding.addhistoryAddimgCl.visibility = View.INVISIBLE
        }

    }

    private fun setAdapter(){
        val adapter = AddHistoryRVAdapter(this, img)
        binding.addhistoryBigImageRv.adapter = adapter
        binding.addhistoryBigImageRv.layoutManager = LinearLayoutManager(this).also { it.orientation = LinearLayoutManager.HORIZONTAL }
        binding.addhistoryBigImageRv.setHasFixedSize(true)
    }

//    private fun createConfig(): ImagePickerConfig{
//        return ImagePickerConfig {
//            mode = ImagePickerMode.MULTIPLE
//            arrowColor = Color.WHITE
//            imageTitle = "이미지를 선택하세요"
//            isShowCamera = true
//            limit = 5
//            returnMode = ReturnMode.NONE
//        }
//    }

//    Request로 보낼 내용 얻는 함수
    private fun getContent() : AddHistoryRequest {
        val title : String = binding.addhistoryAddtitleEt.text.toString()
        val content : String = binding.addhistoryAddcontentEt.text.toString()
        val jsonObject = JSONObject()
        jsonObject.put("title", title)
        jsonObject.put("content", content)

        Log.d("CHECK", jsonObject.toString())
        return AddHistoryRequest(null, jsonObject)
    }

//    History 정보를 서버에 전달
    private fun addHistory(){
        val addHistoryService = AddHistoryService()
        addHistoryService.setAddHistoryView(this)
        addHistoryService.addHistory(getContent())
    }

    override fun onAddHistorySuccess(code: Int, message : String){
        //새로운 history 생성
        finish()
        Log.d("ADDHISTORY/SUCCESS", "서버 연결에 성공했습니다. ")
    }

    override fun onAddHistoryFailure() {
        Log.d("ADDHISTORY/FAILURE", "서버 연결에 실패하였습니다. ")
    }

}