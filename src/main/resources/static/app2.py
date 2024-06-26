from flask import Flask, request, jsonify, send_from_directory
import spacy
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity
import os

app = Flask(__name__)

# Carrega o modelo em português do spaCy
nlp = spacy.load('pt_core_news_sm')

# Define o diretório onde está o arquivo index.html
html_dir = os.path.join(os.path.dirname(os.path.abspath(__file__)), 'C:/Users/MENICIO JR/Desktop/Web')

# Define o diretório onde estão os arquivos estáticos (imagens, CSS, etc.)
static_dir = os.path.join(os.path.dirname(os.path.abspath(__file__)), 'static')

# Função para extrair as entidades da pergunta
def extrair_entidades(texto):
    doc = nlp(texto)
    entidades = [entidade.text for entidade in doc.ents]
    return entidades

@app.route('/')
def index():
    return send_from_directory(html_dir, 'index2.html')

@app.route('/static/<path:filename>')
def serve_static(filename):
    return send_from_directory(static_dir, filename)

@app.route('/processar_pergunta', methods=['POST'])
def processar_pergunta():
    data = request.get_json()

    # Obtém a pergunta da requisição
    pergunta = data['pergunta']

    # Carrega as frases dos templates a partir de um arquivo
    with open('perguntas_de_interesse.txt', 'r', encoding='utf-8') as file:
        frases_templates = file.read().splitlines()

    # Função para calcular similaridade
    def calcular_similaridade(frase_usuario, frases_templates):
        # Analisa a frase do usuário
        analise_usuario = nlp(frase_usuario)
        entidades_usuario = extrair_entidades(frase_usuario)

        # Inicializa as entidades da frase mais similar e a similaridade máxima
        entidades_mais_semelhantes = {}
        similaridade_maxima = 0

        # Itera sobre as frases do conjunto de templates
        for frase_template in frases_templates:
            # Analisa a frase do template
            analise_template = nlp(frase_template)
            entidades_template = extrair_entidades(frase_template)

            # Calcula a similaridade do cosseno entre as entidades do usuário e do template
            texto_usuario = ' '.join([entidades_usuario['nsubj'] or '', entidades_usuario['obj'] or '', ' '.join(entidades_usuario['nmod'])])
            texto_template = ' '.join([entidades_template['nsubj'] or '', entidades_template['obj'] or '', ' '.join(entidades_template['nmod'])])

            vetorizador = CountVectorizer().fit_transform([texto_usuario, texto_template])
            similaridade = cosine_similarity(vetorizador)[0][1]

            # Atualiza a similaridade máxima e as entidades da frase mais similar encontrada
            if similaridade > similaridade_maxima:
                similaridade_maxima = similaridade
                entidades_mais_semelhantes = entidades_template

        return entidades_usuario, entidades_mais_semelhantes, similaridade_maxima

    # Chama a função para calcular a similaridade
    entidades_usuario, entidades_mais_semelhantes, similaridade_maxima = calcular_similaridade(pergunta, frases_templates)

    # Retorna a resposta para a página HTML
    resposta = {
        'pergunta_proxima': frases_templates[0],  # Modifique para pegar a pergunta mais similar
        'similaridade': similaridade_maxima,
        'entidades': entidades_usuario  # Retorna as entidades da pergunta do usuário
    }
    return jsonify(resposta)

if __name__ == '__main__':
    app.run(debug=True)
