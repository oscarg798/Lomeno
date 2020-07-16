package com.oscarg798.lomeno.exceptions

class LogSourceNotSupportedException(name: String): IllegalArgumentException("The event $name is not supported by this logger")