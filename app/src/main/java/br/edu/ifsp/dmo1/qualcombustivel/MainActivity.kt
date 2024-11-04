package br.edu.ifsp.dmo1.qualcombustivel

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import br.edu.ifsp.dmo1.qualcombustvel.R
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var valorEtanol : EditText
    private lateinit var valorGasolina : EditText
    private lateinit var resultadoText : TextView
    private lateinit var button : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coletaInfosViews()
        setClick()


    }

    private fun coletaInfosViews(){
        valorGasolina = findViewById(R.id.valorGasolina)
        valorEtanol = findViewById(R.id.valorEtanol)
        resultadoText = findViewById(R.id.resultado)
        button = findViewById(R.id.button)
    }

   private fun verificaQualOmelhor(){
        if (valorGasolina.text.toString().isEmpty() || valorEtanol.text.toString().isEmpty()){
            Toast.makeText(
                this,
                "Informe o valor dos dois combustíveis",
                Toast.LENGTH_SHORT
            ).show()
            resultadoText.text = ""
        }else{
            val gasolina = converteValorParaDouble(valorGasolina)
            val etanol = converteValorParaDouble(valorEtanol)

            val resultado = etanol / gasolina

            if (resultado < 0.7){
                resultadoText.text = getString(R.string.resultado_etanol)
            }else{
                resultadoText.text = getString(R.string.resultado_gasolina)
            }
        }


    }

    private fun converteValorParaDouble(valor : EditText): Double{
        return try{
            valor.text.toString().toDouble()
        }catch (e : NumberFormatException){
            Toast.makeText(this, "Valor Inválido", Toast.LENGTH_SHORT).show()
            0.0
        }

    }

    override fun onClick(v: View) {
        if (v == button){
            verificaQualOmelhor()
        }
    }

    private fun setClick(){
        button.setOnClickListener(this)
    }
}