package com.example.myapplication.paintapp
import android.graphics.Canvas
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.PopupMenu

import com.example.myapplication.paintapp.PaintView.Companion.canvass
import com.example.myapplication.paintapp.PaintView.Companion.currentBaruch
import com.example.myapplication.paintapp.PaintView.Companion.paintBaruch
import com.example.myapplication.paintapp.PaintView.Companion.path

import com.example.myapplication.paintapp.databinding.ActivityMainBinding
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object{
        var statue : Int?=1 ;
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        setupActionBar()


    }

    private fun setupActionBar() {
        setSupportActionBar(binding.toolbarTools)
        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.title = ""

        }
    }

    private fun currentColor(color: Int) {
        currentBaruch = color
        path = android.graphics.Path()
         canvass = Canvas()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.draw -> {
                statue = 1
            }
            R.id.draw_line -> {
                statue = 2

            }
            R.id.color_pallet->{
                popup()
            }
            R.id.draw_rec->{
                statue = 3
            }
            R.id.draw_ellips->{
                statue==4
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun popup() {

            val popupMenu = PopupMenu(this, binding.toolbarTools)
            popupMenu.menuInflater.inflate(R.menu.popupmenu, popupMenu.menu)

                try {
                    val popup = PopupMenu::class.java.getDeclaredField("mPopup")
                    popup.isAccessible = true
                    val menu = popup.get(popupMenu)
                    menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                        .invoke(menu, true)
                    popupMenu.setOnMenuItemClickListener {
                        when(it.itemId){
                            R.id.red ->{
                                paintBaruch.color = Color.RED
                                currentColor(paintBaruch.color)
                                true
                            }
                            R.id.blue ->{
                            paintBaruch.color = Color.BLUE
                            currentColor(paintBaruch.color)
                            true
                        }
                            R.id.green ->{
                                paintBaruch.color = Color.GREEN
                                currentColor(paintBaruch.color)
                                true
                            }
                            R.id.black ->{
                                paintBaruch.color = Color.BLACK
                                currentColor(paintBaruch.color)
                                true
                            }

                            else->{
                                false
                            }
                        }
                    }
                }catch (e:Exception){
                    e.printStackTrace()

                } finally {
                    popupMenu.show()

            }
        popupMenu.show()
            }
}