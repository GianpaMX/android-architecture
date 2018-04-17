package io.github.gianpamx.android.architecture.gallery

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import dagger.android.AndroidInjection
import io.github.gianpamx.androidarchitecture.R
import kotlinx.android.synthetic.main.gallery_activity.*
import javax.inject.Inject

class GalleryActivity : AppCompatActivity() {

    @Inject
    lateinit var galleryViewModel: GalleryViewModel

    lateinit var galleryAdapter: GalleryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState)

        setContentView(R.layout.gallery_activity)

        galleryAdapter = GalleryAdapter(this)
        configureRecyclerView()

        galleryViewModel.name.observe(this, nameObserver)
        galleryViewModel.images.observe(this, imagesObserver)
    }

    private fun configureRecyclerView() {
        galleryRecyclerView.setHasFixedSize(true)
        galleryRecyclerView.isDrawingCacheEnabled = true;
        galleryRecyclerView.layoutManager = GridLayoutManager(this, resources.getInteger(R.integer.gallery_span_count))
        galleryRecyclerView.adapter = galleryAdapter
    }

    private val nameObserver = Observer<String> {
        greetingEditText.text = getString(R.string.gallery_greeting, it)
    }

    private val imagesObserver = Observer<List<String>> {
        it?.let { galleryAdapter.replaceImages(it) }
    }
}
