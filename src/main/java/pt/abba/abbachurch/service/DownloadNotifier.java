package pt.abba.abbachurch.service;

import io.github.gaeqs.javayoutubedownloader.stream.download.DownloadStatus;
import io.github.gaeqs.javayoutubedownloader.stream.download.StreamDownloader;
import io.github.gaeqs.javayoutubedownloader.stream.download.StreamDownloaderNotifier;

public class DownloadNotifier implements StreamDownloaderNotifier {
    @Override
    public void onStart(StreamDownloader streamDownloader) {
        System.out.println(DownloadStatus.READY);
    }

    @Override
    public void onDownload(StreamDownloader streamDownloader) {
        System.out.println(DownloadStatus.DOWNLOADING);
    }

    @Override
    public void onFinish(StreamDownloader streamDownloader) {
        System.out.println(DownloadStatus.DONE);
    }

    @Override
    public void onError(StreamDownloader streamDownloader, Exception e) {
        System.out.println("download error");
    }
}
