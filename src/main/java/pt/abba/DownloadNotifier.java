package pt.abba;

import io.github.gaeqs.javayoutubedownloader.stream.download.StreamDownloader;
import io.github.gaeqs.javayoutubedownloader.stream.download.StreamDownloaderNotifier;

public class DownloadNotifier implements StreamDownloaderNotifier {
    @Override
    public void onStart(StreamDownloader streamDownloader) {
        System.out.println(streamDownloader.getStatus());
    }

    @Override
    public void onDownload(StreamDownloader streamDownloader) {
        System.out.println(streamDownloader.getStatus());
    }

    @Override
    public void onFinish(StreamDownloader streamDownloader) {
        System.out.println(streamDownloader.getStatus());
    }

    @Override
    public void onError(StreamDownloader streamDownloader, Exception e) {
        System.out.println(streamDownloader.getStatus());
    }
}
