package pt.abba;

import io.github.gaeqs.javayoutubedownloader.JavaYoutubeDownloader;
import io.github.gaeqs.javayoutubedownloader.decoder.MultipleDecoderMethod;
import io.github.gaeqs.javayoutubedownloader.stream.StreamOption;
import io.github.gaeqs.javayoutubedownloader.stream.YoutubeVideo;
import io.github.gaeqs.javayoutubedownloader.stream.download.DownloadStatus;
import io.github.gaeqs.javayoutubedownloader.stream.download.StreamDownloader;

import java.io.File;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Entre com a url");

        String url = myObj.nextLine();
        System.out.println("Url is: " + url);
        myObj.close();
        download(url);
    }

    public static void download(String url) {
        YoutubeVideo video = JavaYoutubeDownloader.decodeOrNull(url, MultipleDecoderMethod.OR, "html", "embedded");
        StreamOption option = video.getStreamOptions().stream()
                .filter(target -> target.getType().hasVideo() && target.getType().hasAudio())
                .min(Comparator.comparingInt(o -> o.getType().getVideoQuality().ordinal())).orElse(null);
        if (option == null) {
            return;
        };
        System.out.println(option.getType());
        File file = new File(new File("/Users/marcosferreira/musics"), video.getTitle() + "." + option.getType().getContainer().toString().toLowerCase());
        StreamDownloader downloader = new StreamDownloader(option, file, new DownloadNotifier());
        DownloadStatus status = downloader.getStatus();
        System.out.println(status);
         new Thread(downloader).start();
    }
}