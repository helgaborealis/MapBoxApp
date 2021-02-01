package net.borlis.mapboxandroidapp

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.maps.Style.OnStyleLoaded
import com.mapbox.mapboxsdk.style.layers.PropertyFactory.*
import com.mapbox.mapboxsdk.style.layers.SymbolLayer
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource
import kotlinx.android.synthetic.main.fragment_map.*
import net.borlis.mapboxandroidapp.extensions.observeNonNull
import net.borlis.mapboxandroidapp.extensions.setVisibility
import org.koin.androidx.viewmodel.ext.android.viewModel


class MapFragment : Fragment() {

    private val SOURCE_ID: String = "SOURCE_ID"
    private val ICON_ID = "ICON_ID"
    private val LAYER_ID = "LAYER_ID"

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
        viewModel.buildingsPointers.observeNonNull(viewLifecycleOwner) { list ->
            addPointersToTheMap(list)
        }
        viewModel.filterPointers.observeNonNull(viewLifecycleOwner) { list ->
            addPointersToTheMap(list)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    filterPointers(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
        viewModel.getBuildings()
    }

    private fun filterPointers(query: String) {
        viewModel.filterPointers(query)
    }

    private fun addPointersToTheMap(list: List<Feature>) {
        mapView.getMapAsync { mapBoxMap ->
            mapBoxMap.setStyle(
                Style.Builder()
                    .fromUri("mapbox://styles/mapbox/cjf4m44iw0uza2spb3q0a7s41")
                    .withImage(
                        ICON_ID, BitmapFactory.decodeResource(
                            this.resources,
                            R.drawable.mapbox_marker_icon_default
                        )
                    )
                    .withSource(
                        GeoJsonSource(
                            SOURCE_ID,
                            FeatureCollection.fromFeatures(list)
                        )
                    )
                    .withLayer(
                        SymbolLayer(LAYER_ID, SOURCE_ID)
                            .withProperties(
                                iconImage(ICON_ID),
                                iconAllowOverlap(true),
                                iconIgnorePlacement(true)
                            )
                    ), OnStyleLoaded {

                })
            // FIXME: 01.02.2021  should be rewritten to fit all the pointers
            val position = CameraPosition.Builder()
                .target(
                    LatLng(
                        (list.first().geometry() as Point).latitude(),
                        (list.first().geometry() as Point).longitude()
                    )
                )
                .zoom(10.0)
                .tilt(20.0)
                .build()

            mapBoxMap.animateCamera(
                CameraUpdateFactory.newCameraPosition(
                    position
                )
            )

            mapBoxMap.addOnMapClickListener {
                val pixel = mapBoxMap.projection.toScreenLocation(it)
                val features = mapBoxMap.queryRenderedFeatures(pixel)
                if (features.size > 0) {
                    val feature = features[0]
                    openDetailView(feature)
                }
                true
            }
        }
    }


    private fun openDetailView(feature: Feature) {
        activity?.let {
            BuildingDetailsDialog(it, feature).show()
        }
    }

    companion object {
        private const val TAG = "MapFragment"
    }
}