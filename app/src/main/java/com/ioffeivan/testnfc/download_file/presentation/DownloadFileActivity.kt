package com.ioffeivan.testnfc.download_file.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ioffeivan.testnfc.R
import com.ioffeivan.testnfc.download_file.utils.DownloadFileConstants
import com.ioffeivan.testnfc.databinding.ActivityDownloadFileBinding
import com.ioffeivan.testnfc.read_data.presentation.MainActivity
import com.tonyodev.fetch2.Download
import com.tonyodev.fetch2.Error
import com.tonyodev.fetch2.Fetch
import com.tonyodev.fetch2.Fetch.Impl.getInstance
import com.tonyodev.fetch2.FetchConfiguration
import com.tonyodev.fetch2.FetchListener
import com.tonyodev.fetch2.NetworkType
import com.tonyodev.fetch2.Priority
import com.tonyodev.fetch2.Request
import com.tonyodev.fetch2core.DownloadBlock
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class DownloadFileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDownloadFileBinding

    private var fetch: Fetch? = null

    private val pathForDownloadedFile by lazy {
        "${filesDir.path}/${DownloadFileConstants.FILE_NAME_FOR_DOWNLOAD}"
    }

    private val pathForSavedFile by lazy {
        "${filesDir.path}/${DownloadFileConstants.FILE_NAME_FOR_SAVE}"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDownloadFileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFetch()

        binding.buttonDownloadFile.setOnClickListener {
            binding.textViewProgress.text = getString(R.string.percent, 0)
            binding.progressBar.progress = 0
            downloadFile(
                url = DownloadFileConstants.URL,
                filePath = pathForDownloadedFile,
                onProgress = { progress ->
                    binding.progressBar.progress = progress
                    binding.textViewProgress.text = getString(R.string.percent, progress)
                },
                onComplete = {
                    binding.textViewProgress.text = "Файл успешно скачался"
                },
                onError = {
                    binding.textViewProgress.text = it?.message
                }
            )
        }

        binding.buttonOpenMainActivity.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initFetch() {
        val fetchConfiguration: FetchConfiguration = FetchConfiguration.Builder(this)
            .setDownloadConcurrentLimit(1)
            .build()

        fetch = getInstance(fetchConfiguration)
    }

    private fun downloadFile(
        url: String,
        filePath: String,
        onProgress: (Int) -> Unit,
        onComplete: () -> Unit,
        onError: (Throwable?) -> Unit,
    ) {
        val request = createRequest(url = url, filePath = filePath)

        fetch?.enqueue(request)
        fetch?.addListener(
            object : FetchListener {
                override fun onError(download: Download, error: Error, throwable: Throwable?) {
                    onError(throwable)
                }

                override fun onProgress(
                    download: Download,
                    etaInMilliSeconds: Long,
                    downloadedBytesPerSecond: Long
                ) {
                    onProgress(download.progress)
                }

                override fun onCompleted(download: Download) {
                    val downloadedFile = File(pathForDownloadedFile)
                    val savedFile = File(pathForSavedFile)

                    if (downloadedFile.exists()) {
                        savedFile.delete()
                    }
                    downloadedFile.renameTo(savedFile)

                    onComplete()
                }

                override fun onQueued(download: Download, waitingOnNetwork: Boolean) {}
                override fun onRemoved(download: Download) {}
                override fun onResumed(download: Download) {}
                override fun onStarted(
                    download: Download,
                    downloadBlocks: List<DownloadBlock>,
                    totalBlocks: Int
                ) {}
                override fun onWaitingNetwork(download: Download) {}
                override fun onAdded(download: Download) {}
                override fun onCancelled(download: Download) {}
                override fun onPaused(download: Download) {}
                override fun onDeleted(download: Download) {}
                override fun onDownloadBlockUpdated(
                    download: Download, downloadBlock: DownloadBlock, totalBlocks: Int
                ) {}
            }
        )
    }

    private fun createRequest(
        url: String,
        filePath: String,
    ): Request {
        return Request(url = url, file = filePath).apply {
            priority = Priority.HIGH
            networkType = NetworkType.ALL
        }
    }
}