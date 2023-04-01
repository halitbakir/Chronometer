package com.halitbakir.chronometer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import com.halitbakir.chronometer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var zamaniDurdur: Long = 0 //kronometreyi durdurup devam ettirince kaldığı yerden devamı için değişken oluşturduk

        binding.btnStart.setOnClickListener {
            binding.kronometre.base = SystemClock.elapsedRealtime()+zamaniDurdur//kronometre içine sistem saatini atadık
            binding.kronometre.start()
            binding.btnStart.visibility = View.GONE //başladıktan sonra start butonu kaybolsun
            binding.btnPause.visibility = View.VISIBLE //görünür olmayan pause butonu gösterme
            binding.imageView.setImageDrawable(getDrawable(R.drawable.pause)) //kırmızı pause halkasını getirme

        }
        binding.btnPause.setOnClickListener {
            zamaniDurdur = binding.kronometre.base-SystemClock.elapsedRealtime()
            binding.kronometre.stop()
            binding.btnPause.visibility = View.GONE
            binding.btnStart.visibility = View.VISIBLE
            binding.imageView.setImageDrawable(getDrawable(R.drawable.start))

        }

        binding.btnReset.setOnClickListener {
            binding.kronometre.base = SystemClock.elapsedRealtime()
            binding.kronometre.stop()
            zamaniDurdur = 0
            binding.btnPause.visibility = View.GONE
            binding.btnStart.visibility = View.VISIBLE
            binding.imageView.setImageDrawable(getDrawable(R.drawable.start))

        }

    }
}