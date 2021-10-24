package mx.tecnm.tepic.mghs.u2_practica2_juego_de_las_moscas

import android.graphics.Canvas
import android.graphics.Paint

class textoLienzo(equis:Float, ye:Float, text:String) {
    var x = equis
    var y = ye
    var texto = text

    fun escribir(c: Canvas){
        c.drawText(texto, x, y, Paint())
    }
}