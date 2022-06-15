package com.example.menus

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    lateinit var button: Button
    lateinit var imgBtn: ImageButton
    lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.popup)
        imgBtn = findViewById(R.id.imageButton)
        textView = findViewById(R.id.textView)
        registerForContextMenu(textView)
        buttonListener()
        imageListener()
    }

    private fun buttonListener(){

        button.setOnClickListener {
            var popupMenu = PopupMenu(this, button)
            popupMenu.inflate(R.menu.popup_menu_items)

            popupMenu.setOnMenuItemClickListener(object: PopupMenu.OnMenuItemClickListener{
                override fun onMenuItemClick(item: MenuItem?): Boolean {
                    var boolean = when(item?.itemId) {
                        R.id.item1 -> {
                            Toast.makeText(applicationContext,"Clicked on Item 1",Toast.LENGTH_SHORT).show()
                            true
                        }

                        R.id.item2 -> {
                            Toast.makeText(applicationContext, "Clicked on Item 2", Toast.LENGTH_LONG).show()
                            true
                        }

                        R.id.item3 -> {
                            Toast.makeText(applicationContext, "Clicked on Item 2", Toast.LENGTH_SHORT).show()
                            true
                        }
                        else -> {
                            false
                        }
                    }

                    return boolean
                }

            })

            popupMenu.show()
        }

    }

    private fun imageListener(){
          var temp = 1
        imgBtn.setOnClickListener {
            var popupWindow = PopupWindow(this)
            var layoutInflater = LayoutInflater.from(this)
            var view = layoutInflater.inflate(R.layout.popup_window, null)
            var image = view.findViewById<ImageView>(R.id.image)
            image.setOnClickListener {
                popupWindow.dismiss()
            }
            popupWindow.contentView = view
            popupWindow.showAsDropDown(imgBtn)
        }


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var tempText = ""
        var temp = 1
        var boolean = when(item.itemId){

            R.id.main -> {
                temp = 0
                true
            }
            R.id.about_us -> {
                tempText = "Clicked on About us"
                true
            }
            R.id.home ->{
                tempText = "Clicked on Home"
                true
            }
            R.id.settings -> {
                tempText = "Clicked on Settings"
                true
            }
            else -> super.onOptionsItemSelected(item)

        }

        if(temp==1) {
            Toast.makeText(this, tempText, Toast.LENGTH_SHORT).show()
        }
         return boolean
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        var submenu = menu?.getItem(0)?.subMenu
        submenu?.getItem(0)?.isEnabled = false
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.my_menu,menu)
        return true
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?)
    {
        menuInflater.inflate(R.menu.context_menu, menu)
        menu?.setHeaderTitle("Choose Colour")
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when (item.itemId){
            R.id.red -> {
                textView.setTextColor(resources.getColor(R.color.red))
                true
            }
            R.id.blue -> {
                textView.setTextColor(resources.getColor(R.color.blue))
                true
            }
            R.id.green -> {
                textView.setTextColor(resources.getColor(R.color.green))
                true
            }else -> {
                super.onContextItemSelected(item)
            }
        }


    }

}