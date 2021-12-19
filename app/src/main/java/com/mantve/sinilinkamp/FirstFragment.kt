package com.mantve.sinilinkamp

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mantve.sinilinkamp.databinding.FragmentFirstBinding
import com.polidea.rxandroidble2.RxBleClient
import com.polidea.rxandroidble2.RxBleConnection
import com.polidea.rxandroidble2.RxBleDevice
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val macAddress: String = "AA:BB:CC:DD:EE:FF"

    private var _binding: FragmentFirstBinding? = null
    private lateinit var rxBleClient: RxBleClient

    @Volatile
    private var connection: RxBleConnection? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root

    }

    private fun onConnectionReceived(this_connection: RxBleConnection) {
        connection = this_connection
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generateEQButtons()
        generateInputButtons()
        generatePlaybackButtons()

        rxBleClient = RxBleClient.create(requireContext())
        val device: RxBleDevice = rxBleClient.getBleDevice(macAddress)

        device.establishConnection(false).subscribeOn(Schedulers.io())
            .subscribe(this::onConnectionReceived)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //TODO: make it read the current status
    private fun sendBTStuff(data: ByteArray) {

        connection?.writeCharacteristic(
            UUID.fromString("0000AE10-0000-1000-8000-00805F9B34FB"),
            data
        )?.subscribe()
    }

    //TODO: figure out how to generalize these 3 functions
    private fun generateEQButtons() {
        val gridLayout: GridLayout = fragment_first_EQ
        for (i in EQ.values()) {
            val button = Button(context)
            button.text = i.name
            button.setBackgroundColor(Color.parseColor("#aa00ff"))
            button.setOnClickListener { sendBTStuff(i.payload) }
            gridLayout.addView(button)
        }
    }

    private fun generatePlaybackButtons() {
        val gridLayout: GridLayout = fragment_first_Playback
        for (i in Playback.values()) {
            val button = Button(context)
            button.text = i.name
            button.setBackgroundColor(Color.parseColor("#bbc8ff"))
            button.setOnClickListener { sendBTStuff(i.payload) }
            gridLayout.addView(button)
        }
    }

    private fun generateInputButtons() {
        val gridLayout: GridLayout = fragment_first_Input
        for (i in Input.values()) {
            val button = Button(context)
            button.text = i.name
            button.setBackgroundColor(Color.parseColor("#df1587"))
            button.setOnClickListener { sendBTStuff(i.payload) }
            gridLayout.addView(button)
        }
    }
}