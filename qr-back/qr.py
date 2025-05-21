from flask import Flask, request, jsonify, send_file
import qrcode
import io
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

@app.route('/generate', methods=['POST'])
def generate_qr():
    data = request.json
    content = data.get('content')
    if not content:
        return jsonify({'error': 'No content provided'}), 400

    qr = qrcode.make(content)
    buf = io.BytesIO()
    qr.save(buf, format='PNG')
    buf.seek(0)

    return send_file(buf, mimetype='image/png')

if __name__ == '__main__':
    app.run(port=5000, debug=True)
