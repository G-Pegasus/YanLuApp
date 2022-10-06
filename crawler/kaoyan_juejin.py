import json

import requests
import csv
import jsonpath

url = "https://api.juejin.cn/search_api/v1/search?aid=2608&uuid=7127513622519186951&spider=0"

payload = {
    "key_word": "考研",
    "id_type": 0,
    "cursor": "0",
    "limit": 20,
    "search_type": 0,
    "sort_type": 0,
    "version": 1,
    "uuid": "7127513622519186951",
    "ab_info": "{}"
}

headers = {
    "cookie": '_ga=GA1.2.444515578.1659503589; _tea_utm_cache_2608=undefined; __tea_cookie_tokens_2608=%257B%2522web_id%2522%253A%25227127513622519186951%2522%252C%2522user_unique_id%2522%253A%25227127513622519186951%2522%252C%2522timestamp%2522%253A1659503589689%257D; MONITOR_WEB_ID=0beddcce-51a4-490d-b1a9-d42a144cdac9; passport_csrf_token=eb26b5f30b4db1de424cd80161f0acfd; passport_csrf_token_default=eb26b5f30b4db1de424cd80161f0acfd; odin_tt=c4de3ac7f7ffd7fc25a7751da0733fa945b4d10de99e691435788f2ce01d5cfc19012613e4ca8e65a8a1c003bfb84195f9085f8458f39ad7c96471e9ff316c47; n_mh=9-mIeuD4wZnlYrrOvfzG3MuT6aQmCUtmr8FxV8Kl8xY; sid_guard=5189057cf6c753edb1813b3f9c86a15b%7C1664154135%7C31536000%7CTue%2C+26-Sep-2023+01%3A02%3A15+GMT; uid_tt=44ff4e813cc089df8ffb58d582959942; uid_tt_ss=44ff4e813cc089df8ffb58d582959942; sid_tt=5189057cf6c753edb1813b3f9c86a15b; sessionid=5189057cf6c753edb1813b3f9c86a15b; sessionid_ss=5189057cf6c753edb1813b3f9c86a15b; sid_ucp_v1=1.0.0-KDY3Nzg4ZGExODI1NWJkNTUzN2NhMDc3YzI1ZDllNjEwOTUzYzhjMWUKFgjnktD8sIyvBhCX9MOZBhiwFDgIQDgaAmxmIiA1MTg5MDU3Y2Y2Yzc1M2VkYjE4MTNiM2Y5Yzg2YTE1Yg; ssid_ucp_v1=1.0.0-KDY3Nzg4ZGExODI1NWJkNTUzN2NhMDc3YzI1ZDllNjEwOTUzYzhjMWUKFgjnktD8sIyvBhCX9MOZBhiwFDgIQDgaAmxmIiA1MTg5MDU3Y2Y2Yzc1M2VkYjE4MTNiM2Y5Yzg2YTE1Yg; _gid=GA1.2.1524308943.1664967796',
    "user-agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36",
    "referer": "https://juejin.cn/",
    "accept-encoding": "gzip, deflate",
    "accept-language": "zh-CN,zh;q=0.9",
    "accept": "*/*",
    "cache-control": "no-cache",
    "sec-ch-ua": '"Google Chrome";v="105", "Not)A;Brand";v="8", "Chromium";v="105"',
    "sec-ch-ua-mobile": "?0",
    "sec-ch-ua-platform": '\'"Windows\"',
    "sec-fetch-dest": "empty",
    "sec-fetch-mode": "cors",
    "sec-fetch-site": "same-origin",
    "pragma": "no-cache",
    "content-length": "498",
    "content-type": "application/json",
    "origin": "https://juejin.cn"
}

resp = requests.post(url=url, data=json.dumps(payload), headers=headers)
resp.encoding = 'utf-8'

# with open("kaoyan_juejin.json", mode="w", encoding='utf-8') as f:
#     text = resp.text
#     f.write(text)
# print('over!')
# f.close()

text = json.loads(resp.text)

articles = []
title = jsonpath.jsonpath(text, "$.data[*].result_model.article_info.title")
article_url = jsonpath.jsonpath(text, "$.data[*].result_model.article_info.article_id")
article_id = []
title_eng = []
for i in title:
    title_eng.append(i.replace(",", "，"))
for i in article_url:
    article_id.append("https://juejin.cn/post/" + i)
description = jsonpath.jsonpath(text, "$.data[*].result_model.article_info.brief_content")
articles.append(title_eng)
articles.append(article_id)
articles.append(description)
print(articles)

with open('kaoyan_juejin.csv', mode='w', encoding='utf-8') as f:
    for i in articles:
        csv.writer(f).writerow(i)
f.close()
