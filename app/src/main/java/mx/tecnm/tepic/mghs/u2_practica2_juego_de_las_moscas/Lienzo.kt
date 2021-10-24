package mx.tecnm.tepic.mghs.u2_practica2_juego_de_las_moscas

import android.app.AlertDialog
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast

class Lienzo(p:MainActivity): View(p) {
    val principal = p
    val hiloMosca = movimientoMoscas(this)
    var mensaje = ""
    var conta = 0

    init{
        hiloMosca.start()
    }

    override fun onDraw(c: Canvas) {
        super.onDraw(c)


        (0..(hiloMosca.cantidad-1)).forEach{
            hiloMosca.plaga[it].pintar(c)
        }

        var p = Paint()
        p.color = Color.RED
        p.textSize = 70f
        c.drawText("${mensaje}", 100f, 550f, p)
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        super.onTouchEvent(e)

        val accion = e.action

        if(hiloMosca.stillGoing == true){
            if(accion == MotionEvent.ACTION_DOWN){
                (0..(hiloMosca.cantidad-1)).forEach{
                    if (hiloMosca.plaga[it].viva == true) hiloMosca.plaga[it].estaEnArea(e.x, e.y)
                }
            }
        }
        invalidate()
        return true
    }


}

class movimientoMoscas(p:Lienzo):Thread(){
    val puntero = p
    val plaga = ArrayList<Mosca>()
    var cantidad = 0
    var contadorRepe = 0
    var stillGoing = true

    init {
        cantidad = ((Math.random()*21)+80).toInt()
        (1..cantidad).forEach{
            val mosca = Mosca(puntero)
            plaga.add(mosca)
        }
    }

    override fun run() {
        super.run()
        while (stillGoing) {
            if (contadorRepe == 1500) {
                stillGoing = false
                puntero.mensaje = "Se acabo el tiempo"
            }
            if (puntero.conta == plaga.size){
                stillGoing = false
                puntero.mensaje = "Ganaste!!"
            }
            (0..(cantidad - 1)).forEach {
                plaga[it].mover()
            }
            contadorRepe++
            puntero.principal.runOnUiThread {
                puntero.invalidate()
            }
            sleep(40)
        }
    }
}
