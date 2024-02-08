package pt.abba.abbachurch.service;

import io.github.gaeqs.javayoutubedownloader.stream.download.DownloadStatus;
import io.github.gaeqs.javayoutubedownloader.stream.download.StreamDownloader;
import io.github.gaeqs.javayoutubedownloader.stream.download.StreamDownloaderNotifier;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DownloadNotifier implements StreamDownloaderNotifier {
    @Override
    public void onStart(StreamDownloader streamDownloader) {
        log.info(DownloadStatus.READY.name());
    }

    @Override
    public void onDownload(StreamDownloader streamDownloader) {
        log.info(DownloadStatus.DOWNLOADING.name());
    }

    @Override
    public void onFinish(StreamDownloader streamDownloader) {
        log.info(DownloadStatus.DONE.name());
    }

    @Override
    public void onError(StreamDownloader streamDownloader, Exception e) {
        log.info("download error");
    }
}
