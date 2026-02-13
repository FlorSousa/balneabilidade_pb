import requests
from bs4 import BeautifulSoup
import re
from loguru import logger
from requests.adapters import HTTPAdapter
from requests.packages.urllib3.util.retry import Retry

retry_strategy = Retry(
    total=3,
    status_forcelist=[429, 500, 502, 503, 504]
)

adapter = HTTPAdapter(max_retries=retry_strategy)
http = requests.Session()
http.headers.update({
    "User-Agent": "MinhaAplicacao/1.0",
    "Accept": "application/json"
})

http.mount("https://", adapter)
http.mount("http://", adapter)

def get_url_from_row(row):
    column_row = row.find("a")
    return False if column_row == None else True
        

def extract_url_list(url):
    page = requests.get(url)
    soup = BeautifulSoup(page.content, "html.parser")
    div_links = list(itertools.filterfalse(lambda content: content.find("a") is None, soup.find_all(class_="content")))
    div_links = [item.find("a").get("href") for item in div_links] if len(div_links) > 1 else []
    tabela = soup.find_all("td")
    links_tabela = list(map(lambda x: x.find("a").get("href"),filter(get_url_from_row, tabela)))
    div_links = div_links+links_tabela
    return div_links
def extract_name(url,i):
    #patterns = [r'(?<=-)\d+(?=_)', r'(?<=-)\d+(?=-)', r'(?<=_)\d+(?=.)', r'(?:-|Balneabilidade)(\d+)_', r'(?<=-)\d+(?=_)', r'(?<=-)\d+(?=.)', r'(\d+_\d+)(?=\.pdf)']
    patterns = [r'(?<=\/)[^\/]+(?=\.pdf)']
    resultado = re.findall(patterns[i], url)
    i+=1
    if resultado != None and len(resultado) >= 1:
        yield resultado
    else:
        yield from extract_name(url,i)

def format_name(name):
    return f"{name[0]}_{name[1]}" if len(name) >1 else f"{name[0][:2]}_{name[0][2:]}"

def extract_download_url_from_json(url):
        x = http.get(url)
        return x.json()["file"]['download']
        
def download_data_to_ocr(url):
    logger.info(f"Baixando de: {url}")
    head_test = http.head(url)
    url = extract_download_url_from_json(url) if head_test.headers["content-type"] == "application/json" else url
    try:
        numero_relatorio = list(extract_name(url=url,i=0))[0]     
        filename = f"pdf/relatorio_{numero_relatorio[0]}.pdf"   
        #filename = f"pdf/relatorio_{format_name(numero_relatorio)}.pdf"    
        if filename == "pdf/relatorio_2023.pdf":
            print(url)
            exit()
        response = http.get(url)
        with open(filename, "wb") as f:
            for chunk in response.iter_content(chunk_size=8192):
                if chunk:
                    f.write(chunk)
    except Exception as e:
        print(url)
        logger.error(f"Erro durante o download de: {url} | Error: {e}")
    
def download_data_doc():
    pass

if __name__ == '__main__':
    import time
    import itertools

    name = f"logs/DOWNLOAD {time.strftime('%X %d-%m-%y')}.log"
    logger.add(name, rotation="4 KB",format="{time} | {level} | {message}")
    logger.info("Iniciando")
    base_path_ocr = "etl/ocr"
    base_path_doc = "etl/doc"
    base_url = ["https://sudema.pb.gov.br/qualidade-do-ambiente/qualidade-dos-mares","https://sudema.pb.gov.br/qualidade-do-ambiente/relatorios-anteriores"]
    
    url_list = list(itertools.chain.from_iterable([extract_url_list(link) for link in base_url]))
    for i in url_list:
        if bool(re.search(r'resolveuid', i)):
            continue
        download_data_to_ocr(i)

        