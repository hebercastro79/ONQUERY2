from flask import Flask, request, jsonify, send_from_directory
import spacy
import os
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity

app = Flask(__name__)

# Carrega o modelo em português do spaCy
nlp = spacy.load('pt_core_news_sm')

# Define o diretório onde está o arquivo index.html
html_dir = os.path.join(os.path.dirname(os.path.abspath(__file__)), 'C:/Users/MENICIO JR/Desktop/Web')

# Define o diretório onde estão os arquivos estáticos (imagens, CSS, etc.)
static_dir = os.path.join(os.path.dirname(os.path.abspath(__file__)), 'static')

# Carrega as perguntas de interesse do arquivo
with open('perguntas_de_interesse.txt', 'r', encoding='utf-8') as file:
    perguntas_interesse = file.read().splitlines()

def extrair_entidades_interesse(analise):
    entidades_interesse = []
    for token in analise:
        if token.dep_ == 'nmod':
            entidades_interesse.append(token.text)
    return entidades_interesse

def calcular_similaridade_perguntas(frase_usuario, perguntas):
    similaridade_maxima = 0
    pergunta_mais_proxima = None

    for pergunta in perguntas:
        # Vetoriza as perguntas
        vetorizador = CountVectorizer().fit_transform([frase_usuario, pergunta])
        similaridade = cosine_similarity(vetorizador)[0][1]

        # Atualiza a similaridade máxima e a pergunta mais próxima
        if similaridade > similaridade_maxima:
            similaridade_maxima = similaridade
            pergunta_mais_proxima = pergunta

    return similaridade_maxima, pergunta_mais_proxima

@app.route('/')
def index():
    return send_from_directory(html_dir, 'new.html')

@app.route('/static/<path:filename>')
def serve_static(filename):
    return send_from_directory(static_dir, filename)

@app.route('/processar_pergunta', methods=['POST'])
def processar_pergunta():
    data = request.get_json()

    # Obtém a pergunta da requisição
    pergunta_usuario = data['pergunta']

    # Calcula a similaridade entre a pergunta do usuário e as perguntas de interesse
    similaridade, pergunta_proxima = calcular_similaridade_perguntas(pergunta_usuario, perguntas_interesse)

    # Analisa a pergunta do usuário
    analise = nlp(pergunta_usuario)
    # Extrai as entidades da pergunta do usuário
    entidades_usuario = extrair_entidades_interesse(analise)

    # Constrói a resposta com a frase desejada
    resposta = {
        'pergunta_proxima': "Qual foi o preço de fechamento da ação da CSN em 08/05/2023?",
        'similaridade': similaridade,
        'entidades_usuario': entidades_usuario
    }

    # Adiciona os dados adicionais solicitados
    resposta['frase_desejada'] = "Qual o preço de fechamento da ação da Vale em 06/05/2024?"

    # Calcula a porcentagem de similaridade
    resposta['porcentagem_similaridade'] = similaridade * 100

    return jsonify(resposta)

if __name__ == '__main__':
    app.run(debug=True)
