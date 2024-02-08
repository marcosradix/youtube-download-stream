package pt.abba.abbachurch.service;

import io.github.gaeqs.javayoutubedownloader.JavaYoutubeDownloader;
import io.github.gaeqs.javayoutubedownloader.decoder.MultipleDecoderMethod;
import io.github.gaeqs.javayoutubedownloader.stream.StreamOption;
import io.github.gaeqs.javayoutubedownloader.stream.YoutubeVideo;
import io.github.gaeqs.javayoutubedownloader.stream.download.StreamDownloader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Comparator;

@Service
@Slf4j
public class DownloadService {

    public void download(String url) {
        Thread thread = null;

        try {

            YoutubeVideo video = JavaYoutubeDownloader.decodeOrNull(url, MultipleDecoderMethod.OR, "html", "embedded");
            StreamOption option = video.getStreamOptions().stream()
                    .filter(target -> target.getType().hasVideo() && target.getType().hasAudio())
                    .min(Comparator.comparingInt(o -> o.getType().getVideoQuality().ordinal())).orElse(null);
            if (option == null) {
                return;
            }
            log.info(option.getType().toString());
            var userFolder = System.getProperty("user.home");
            var userFolderFile = new File(userFolder.concat("/musics"));
            boolean mkdir = userFolderFile.mkdir();
            log.info("create dir: {}", mkdir);
            log.info("userFolder: {}", userFolder);
            File file = new File(userFolderFile, video.getTitle() + "." + option.getType().getContainer().toString().toLowerCase());
            StreamDownloader downloader = new StreamDownloader(option, file, new DownloadNotifier());
            thread = new Thread(downloader);
            thread.start();
        }catch (Exception e) {
            if(thread != null)
                thread.interrupt();
            e.printStackTrace();
            throw new RuntimeException("Not able to download the video");
        }
    }

}
