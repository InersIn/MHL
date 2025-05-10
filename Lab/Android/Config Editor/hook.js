Java.perform(() => {
    var log = Java.use("android.util.Log")
    var LegacyCommandUtil = Java.use('com.mobilehackinglab.configeditor.LegacyCommandUtil');
    
    LegacyCommandUtil.$init.overload('java.lang.String').implementation = function (cmd) {
        log.d("INERSIN", cmd);
        return this.$init(cmd);
    };
})
