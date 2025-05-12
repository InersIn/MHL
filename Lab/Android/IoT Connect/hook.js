Java.perform(function() {
    var BroadcastReceiver = Java.use("android.content.BroadcastReceiver");
    BroadcastReceiver.onReceive.implementation = function(context, intent) {
        console.log("BroadcastReceiver.onReceive called with: " + intent.getAction());
        this.onReceive(context, intent); // call original
    };

    let User = Java.use("com.mobilehackinglab.iotconnect.ROOM.User");
    User["isGuest"].implementation = function () {
        console.log(`User.isGuest is called`);
        let result = this["isGuest"]();
        console.log(`User.isGuest result=${result}`);
        return result;
    };
});