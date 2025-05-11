from flask import Flask, request, send_file
import urllib.parse
import os

app = Flask(__name__)

SO_FILE_PATH = os.path.join(os.path.dirname(__file__), 'www', 'libdocviewer_pro.so')

@app.route('/', defaults={'path': ''})
@app.route('/<path:path>')
def catch_all(path):
    if path.endswith('.so'):
        return send_file(SO_FILE_PATH, mimetype='application/octet-stream')
    else:
        return "nice"

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
