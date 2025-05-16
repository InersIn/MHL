Java.perform(() => {

    let WebAppInterface = Java.use("com.mobilehackinglab.postboard.WebAppInterface");
    WebAppInterface["postMarkdownMessage"].implementation = function (markdownMessage) {
        console.log(`WebAppInterface.postMarkdownMessage is called: markdownMessage=${markdownMessage}`);
        this["postMarkdownMessage"](markdownMessage);
    };

    WebAppInterface["postCowsayMessage"].implementation = function (cowsayMessage) {
        console.log(`WebAppInterface.postCowsayMessage is called: cowsayMessage=${cowsayMessage}`);
        this["postCowsayMessage"](cowsayMessage);
    };

    let Companion = Java.use("CowsayUtil$Companion");
    Companion["runCowsay"].implementation = function (message) {
        console.log(`Companion.runCowsay is called: message=${message}`);
        let result = this["runCowsay"](message);
        console.log(`Companion.runCowsay result=${result}`);
        return result;
    };
})