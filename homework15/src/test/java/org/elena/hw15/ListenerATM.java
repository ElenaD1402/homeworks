package org.elena.hw15;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class ListenerATM implements TestWatcher {

    public void testSuccessful(ExtensionContext context) {
        System.out.println(context.getDisplayName() + " - Passed");
    }

    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println(context.getDisplayName() + " - Failed");
        System.out.println(cause.getMessage());
    }
}
