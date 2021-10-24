package mx.tecnm.tepic.mghs.u2_practica2_juego_de_las_moscas

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint

class Mosca(l:Lienzo) {
    var x = 0f
    var y = 0f
    val screenwidth = 650
    val screenheight = 1300
    var velox = 0f
    var veloy = 0f
    var lienzo = l
    var up = false
    var right = false
    var viva = true
    var apariencia = BitmapFactory.decodeResource(lienzo.resources, R.drawable.mosca)

    init {
        x = (screenwidth/2).toFloat()
        y = (screenheight/2).toFloat()
        velox = ((Math.random()*5)+1).toFloat()
        veloy = ((Math.random()*5)+1).toFloat()
        var derecha = (Math.random()*2).toInt()
        var arriba = (Math.random()*2).toInt()
        if(derecha == 1){
            right = true
        }
        if(arriba == 1){
            up = true
        }
    }

    fun pintar(c: Canvas){
        c.drawBitmap(apariencia,x,y, Paint())
    }

    fun aplastar(){
        viva = false
        apariencia = BitmapFactory.decodeResource(lienzo.resources, R.drawable.splat)
        lienzo.conta += 1
    }

    fun estaEnArea(toqueX:Float, toqueY:Float):Boolean {
        var x2 = x+apariencia.width
        var y2 = y+apariencia.height

        if(toqueX >= x && toqueX <= x2){
            if(toqueY >= y && toqueY <= y2){
                aplastar()
                return true
            }
        }
        return false
    }

    fun mover(){
        if(viva==true){
            //Movimiento Horizontal
            if(right){
                x+=velox
                if((x+apariencia.width) >= screenwidth.toFloat()){
                    right = false
                }
            }
            else{
                x-=velox
                if(x <= 0f) {
                    right = true
                }
            }
            //Movimiento vertical
            if(up){
                y-=veloy
                if(y <= 0f){
                    up = false
                }
            }
            else{
                y+=veloy
                if((y+apariencia.height) >= screenheight.toFloat()) {
                    up = true
                }
            }
        }
    }


}