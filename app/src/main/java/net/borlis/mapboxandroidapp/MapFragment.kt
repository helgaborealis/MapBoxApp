package net.borlis.mapboxandroidapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mapbox.mapboxsdk.maps.*
import kotlinx.android.synthetic.main.fragment_map.*
import net.borlis.mapboxandroidapp.extensions.observeNonNull
import net.borlis.mapboxandroidapp.extensions.setVisibility
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : Fragment() {

    private val viewModel: BuildingsMapViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.inProgress.observeNonNull(viewLifecycleOwner) { isInProgress ->
            loading.setVisibility(isInProgress)
        }
        viewModel.buildingsPointers.observeNonNull(viewLifecycleOwner) {
            // TODO: 30.01.2021  display pointers here
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView.getMapAsync { mapBoxMap ->
            mapBoxMap.setStyle(Style.SATELLITE) {
                // Map is set up and the style has loaded. Now you can add data or make other map adjustments
            }
        }
        viewModel.getBuildings()
    }

    private fun openDetailView() {
        // TODO: 30.01.2021
    }
}