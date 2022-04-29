package inf112.skeleton.app;
import javafx.application.Platform;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

public class BeforeAllTestsExtension implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {

    private static boolean started = false;

    @Override
    public void beforeAll(ExtensionContext context) {
        if (!started) {
            started = true;
            // "Before all tests" startup logic goes here:
            Platform.startup(()->{});

            // The following line registers a callback hook when the root test context is shut down
            context.getRoot().getStore(GLOBAL).put("any unique name", this);
        }
    }

    @Override
    public void close() {
        // "after all tests" logic goes here
    }
}

