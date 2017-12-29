package com.justin.swbot;

import com.justin.swbot.dependencies.DependenciesRegistry;
import com.justin.swbot.util.CommandUtil;
import com.justin.swbot.util.MemImageUtil;

import static org.mockito.Mockito.mock;

public abstract class BaseTest {
    public BaseTest() {
        setupDependenciesRegistry();
    }

    private void setupDependenciesRegistry() {
        DependenciesRegistry.commandUtil = mock(CommandUtil.class);
        DependenciesRegistry.memImageUtil = mock(MemImageUtil.class);
    }
}
