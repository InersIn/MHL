Java.perform(() => {
    Interceptor.attach(Module.findExportByName("libc.so", "system"), {
        onEnter: (args) => {
            var cmd = Memory.readCString(args[0])
            console.log(`dest: ${cmd}`)
        }
    })
})