module jack {
    exports at.fh.hagenberg.aist.jack.data;
    exports at.fh.hagenberg.aist.jack.exception;
    exports at.fh.hagenberg.aist.jack.general;
    exports at.fh.hagenberg.aist.jack.general.function;
    exports at.fh.hagenberg.aist.jack.general.transformer;
    exports at.fh.hagenberg.aist.jack.general.util;
    exports at.fh.hagenberg.aist.jack.math;
    exports at.fh.hagenberg.aist.jack.persistence.core;
    exports at.fh.hagenberg.aist.jack.persistence.filesystem;
    exports at.fh.hagenberg.aist.jack.persistence.filesystem.implementation;
    exports at.fh.hagenberg.aist.jack.random;
    exports at.fh.hagenberg.aist.jack.reflection;
    exports at.fh.hagenberg.aist.jack.stream;
    exports at.fh.hagenberg.aist.jack.string;

    requires static lombok;
    requires core; // This is seshat core. I have no idea how to make that clear ...
}

