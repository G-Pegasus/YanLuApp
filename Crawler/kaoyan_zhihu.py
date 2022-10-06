import json

import requests
import csv
import jsonpath


url = "https://www.zhihu.com/api/v4/search_v3?gk_version=gz-gaokao&t=general&q=%E8%80%83%E7%A0%94%E8%A7%84%E5%88%92&correction=1&offset=0&limit=20&filter_fields=&lc_idx=0&show_all_topics=0&search_source=Filter&vertical=article"

headers = {
    "cookie": '_zap=c5546e78-444a-400a-92eb-0f940573bd78; d_c0="AKCf0d3CWBWPTpYob7tC9AC28OKGb5BDqAQ=|1659513915"; _9755xjdesxxd_=32; YD00517437729195%3AWM_TID=WVHuSry14FVAUFABQFLBCCwcm7J0yLXu; __snaker__id=e8gm6wtYljLYHBCo; _xsrf=bfb3d3a2-026e-4515-aa9a-99d68ca90a7f; Hm_lvt_98beee57fd2ef70ccdd5ca52b9740c49=1664607356,1664854220,1664939939,1664948847; SESSIONID=wz3hiIg7QTE6hJEG7hjLiUWAfjLOFCbWzIOLCtPu8PG; JOID=VFEUAU7cFLB0ObSfZ9gIpsbicst1pGaAG1Py8FOOT_1BVv3VKK4FiRM1s5RjIgmjpx0Z3WDlfY3IKP30yUs3XQo=; osd=V1gQC0nfHbR-PreWY9IPpc_meMx2rWKKHFD79FmJTPRFXPrWIaoPjhA8t55kIQCnrRoa1GTveo7BLPfzykIzVw0=; captcha_session_v2=2|1:0|10:1664948855|18:captcha_session_v2|88:Q0s3K2RrMGo0VGxiRFZmOEh6emhmR0pva0s1amp2d3JxbFhhSXNQSm9Sa0tiQ1lndkI4NzlhZ01QQ2d1U0lMQQ==|08f7aff7ab0003184a881081ec37b2fe5444d6d793235da811cd3ad741970a60; gdxidpyhxdE=%5CgeiJZpCZrmbTaZBZmu3%5CteDRtK01vGtNCzMrQn9rCCkX%2FU0RucvElQg9Pd%2B1NU60J7pcPkk7%2Bfo6vVAgECmtVemcbs0YD%5CGDXnD%2FT5oRIz2VIjEMuCe%2BJ%2BkJvKJDl%5C%5CU0qjigXCjuPfjKYEhUwcIWovPDbjC0aZtBZNHvagQRp1y2dv%3A1664949750898; YD00517437729195%3AWM_NI=D0yjP82%2B%2BMTy2USQ4NdlHBR8C4HWt0rsAFHaQrjqfG%2B9SqVI5WwnqLAexJ0iUtlxY%2BMJhXvs3Bl9Ix2rCmx2kNGb2WRvOKwHcSVWHrmtJuP%2BP8gh8EbhaSZB9r8Rx1jmN1M%3D; YD00517437729195%3AWM_NIKE=9ca17ae2e6ffcda170e2e6eeb8d23ae98889a8eb4290868fa7c45a979b9facd460fcb4f88fe480b1a6a2b0fb2af0fea7c3b92a88f0b78bd334f2f5e5aed773f28b9db2b746a8b488a3d13481ec88b8f73cb2ad8fa7cb6b93aea692e54de9e79d92d543f789b98ff44883bbb8abb36397aca499dc218f96a8b9f579f19ea4dacc74f2ae8d8efb3aa9928983b15d92aa81acf644a18d9ab8cb7aafac8696d32194e8a989bb4fa2b8afb4d94afbaef8b3fb3baab382d2d037e2a3; NOT_UNREGISTER_WAITING=1; z_c0=2|1:0|10:1664948887|4:z_c0|92:Mi4xNnBwTkhnQUFBQUFBb0pfUjNjSllGU1lBQUFCZ0FsVk5sV2dxWkFBQWtzY0MzbWpBWG5tSWRMYzFiRTJpV2xTMWd3|09445b356ad868084960c25a85dfb1b9ee3c86c0502e0035f66215abef092c51; q_c1=4cf3455bed854db289cbdccdc62e9359|1664948893000|1664948893000; tst=r; Hm_lpvt_98beee57fd2ef70ccdd5ca52b9740c49=1664951131; KLBRSID=53650870f91603bc3193342a80cf198c|1664951137|1664948852',
    "user-agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36",
    "referer": "https://www.zhihu.com/search?q=%E8%80%83%E7%A0%94%E8%A7%84%E5%88%92&type=content&utm_content=search_history&vertical=article",
    "accept-encoding": "gzip, deflate",
    "accept-language": "zh-CN,zh;q=0.9",
    "accept": "*/*",
    "cache-control": "no-cache",
    "x-ab-pb": "CsgBGwA/AEcAtABpAWoBdAE7AswC1wLYAk8DUAOgA6EDogO3A/MD9AMzBIwEjQSmBNYEEQUyBVEFiwWMBZ4FMAYxBn4G6wYnB3cHeAfYB9wH3QdnCHQIdgh5CMUI2gg/CUIJYAmNCcMJxAnFCcYJxwnICckJygnLCcwJ0QnxCfQJBApJCmUKawqYCqUKqQq+CsQK1ArdCu0K/Qr+CjsLPAtDC0YLcQt2C4ULhwuNC8AL1wvgC+UL5gssDDgMcQyPDKwMuQzDDMkM+AwSZAAAAAAAAQAAAAAAAAAAAAAAAAAABAQABAABAQAAAQAAAAEAAAAAAgIEAAAGAAAAAQIAAAAAAAAAAAAAAAAAAwAAAAABAAAAAQEBAAAAAQABAAAAAAAFAAIAAAAGAgAAAAAAAAA=",
    "x-api-version": "3.0.91",
    "x-app-za": "OS=Web",
    "x-requested-with": "fetch",
    "x-zse-93": "101_3_3.0",
    "x-zse-96": "2.0_P3Zypk10NVqKsb2H4e6LPGrT2KHTdL38Z90zVxRm4qL0CG2L4/utQQ5qDtjWgyJK",
    "x-zst-81": "3_2.0aR_sn77yn6O92wOB8hPZnQr0EMYxc4f18wNBUgpTQ6nxERFZsRY0-4Lm-h3_tufIwJS8gcxTgJS_AuPZNcXCTwxI78YxEM20s4PGDwN8gGcYAupMWufIeQuK7AFpS6O1vukyQ_R0rRnsyukMGvxBEqeCiRnxEL2ZZrxmDucmqhPXnXFMTAoTF6RhRuLPF6OOhgXGPgN98gtMhCYO8go1Q7gqcgofo7Hf3BXLVUo02R3ODGxBmvO9CUoTv0ppDhxq6JeC6MXLxCSxNgcBBCXfSiCB99HOUC2G5qeL0bpB2HF_OUoMmR3MrHNqoBomuvc1GeXm2Dpqaww1jDLLzBCGYJwL_BeKQLxK5B3MbvN_OG39VqfzGJH1Ybu1hhCYk6H9YBLBNvSMwce91BNOEDCOJHC9uqw9aqSGQUNCSeg9-gSxb920_Cc_sqxYygS8_92m3UVKuJVG8uVBS6XL1GO01rVCSGSVQXe8GBCC",
    "sec-ch-ua": '"Google Chrome";v="105", "Not)A;Brand";v="8", "Chromium";v="105"',
    "sec-ch-ua-mobile": "?0",
    "sec-ch-ua-platform": '\'"Windows\"',
    "sec-fetch-dest": "empty",
    "sec-fetch-mode": "cors",
    "sec-fetch-site": "same-origin"
}

resp = requests.get(url=url, headers=headers)
resp.encoding = 'utf-8'

# with open("kaoyan_zhihu.json", mode="w", encoding='utf-8') as f:
#     text = resp.text\
#         .replace("\\u003ce", "")\
#         .replace("api", "www")\
#         .replace("m\\u003e", "")\
#         .replace("\\u003c/e", "")\
#         .replace("questions", "question")
#     f.write(text)
# print('over!')
# f.close()

text = json.loads(resp.text \
                  .replace("\\u003ce", "") \
                  .replace("api", "www") \
                  .replace("m\\u003e", "") \
                  .replace("\\u003c/e", "") \
                  .replace("questions", "question").replace("answers", "answer"))

articles = []
title = jsonpath.jsonpath(text, "$.data[*].object.title")
article_id = jsonpath.jsonpath(text, "$.data[*].object.id")
article_url = []
for i in article_id:
    article_url.append("https://zhuanlan.zhihu.com/p/" + i)
description = jsonpath.jsonpath(text, "$.data[*].object.excerpt")
articles.append(title)
articles.append(article_url)
articles.append(description)
print(articles)

with open('kaoyan_zhihu.csv', mode='w', encoding='utf-8') as f:
    for i in articles:
        csv.writer(f).writerow(i)
f.close()
