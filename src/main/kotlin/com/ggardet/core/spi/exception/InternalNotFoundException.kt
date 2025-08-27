package com.ggardet.core.spi.exception

import org.springframework.modulith.NamedInterface

@NamedInterface("core-exception-spi")
open class InternalNotFoundException(message: String) : RuntimeException(message)