package com.ggardet

import org.junit.jupiter.api.Test
import org.springframework.modulith.core.ApplicationModules
import org.springframework.modulith.docs.Documenter

internal class ModularityTests {
    private val modules = ApplicationModules.of(SpringModulithApplication::class.java)

    @Test
    fun shouldBeCompliant() {
        modules.verify()
    }

    @Test
    fun writeDocumentationSnippets() {
        Documenter(modules, "doc")
            .writeModuleCanvases()
            .writeModulesAsPlantUml()
            .writeIndividualModulesAsPlantUml()
    }
}
