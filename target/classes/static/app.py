from flask import Flask, request, jsonify, send_from_directory
import spacy
import os

app = Flask(__name__)

# Carrega o modelo em português do spaCy
nlp = spacy.load('pt_core_news_sm')

# Define o diretório onde está o arquivo index.html
html_dir = os.path.join(os.path.dirname(os.path.abspath(__file__)), 'C:/Users/MENICIO JR/Desktop/Web')

# Define o diretório onde estão os arquivos estáticos (imagens, CSS, etc.)

static_dir = os.path.join(os.path.dirname(os.path.abspath(__file__)), 'static')


@app.route('/')
def index():
    return send_from_directory(html_dir, 'index.html')

@app.route('/static/<path:filename>')
def serve_static(filename):
    return send_from_directory(static_dir, filename)

@app.route('/processar_pergunta', methods=['POST'])
def processar_pergunta():
    data = request.get_json()

    # Obtém a pergunta da requisição
    pergunta = data['pergunta']

    # Aqui você pode processar a pergunta como desejar
    # Por exemplo, você pode chamar a função calcular_similaridade() ou executar_query_sparql() com a pergunta

    # Retorna a resposta para a página HTML (neste exemplo, apenas a pergunta)
    return jsonify({'resposta': pergunta})

if __name__ == '__main__':
    app.run(debug=True)
