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

    @Inject
    lateinit var galleryAdapter: GalleryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState)

        setContentView(R.layout.gallery_activity)

        galleryRecyclerView.setHasFixedSize(true)
        galleryRecyclerView.isDrawingCacheEnabled = true;
        galleryRecyclerView.layoutManager = GridLayoutManager(this, 3)
        galleryRecyclerView.adapter = galleryAdapter

        galleryViewModel.name.observe(this, Observer { name ->
            greetingEditText.text = getString(R.string.gallery_greeting, name)
        })

        galleryViewModel.images.observe(this, Observer { images ->
            galleryAdapter.replaceImages(images!!)
        })
    }
}
