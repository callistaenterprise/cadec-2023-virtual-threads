import io.gatling.recorder.GatlingRecorder;
import io.gatling.recorder.config.RecorderPropertiesBuilder;
import java.nio.file.Path;
import scala.Option;

public class Recorder {

  public static void main(String[] args) {
    RecorderPropertiesBuilder props = new RecorderPropertiesBuilder()
        .simulationsFolder(IDEPathHelper.mavenSourcesDirectory.toString())
        .resourcesFolder(IDEPathHelper.mavenResourcesDirectory.toString())
        .simulationPackage("loom")
        .simulationFormatJava();

    GatlingRecorder.fromMap(props.build(), Option.<Path>apply(IDEPathHelper.recorderConfigFile));
  }
}
