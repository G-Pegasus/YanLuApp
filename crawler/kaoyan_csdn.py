import json

import requests
import csv
import jsonpath


url = "https://so.csdn.net/api/v3/search?q=%E8%80%83%E7%A0%94&t=all&p=1&s=0&tm=0&lv=-1&ft=0&l=&u=&ct=-1&pnt=-1&ry=-1&ss=-1&dct=-1&vco=-1&cc=-1&sc=-1&akt=-1&art=-1&ca=-1&prs=&pre=&ecc=-1&ebc=-1&ia=1&dId=&cl=-1&scl=-1&tcl=-1&platform=pc"

headers = {
    "cookie": 'uuid_tt_dd=10_30853070190-1659503555660-810570; __gads=ID=1189d75690f54448-2248983e6bd500f4:T=1659521211:RT=1659521211:S=ALNI_Mbi6TGiDv2Dtn68y6qCPOrzoOTwoQ; UserName=m0_51276753; UserInfo=d73d762f6a0e4ad7b3dc61fe0d05d70b; UserToken=d73d762f6a0e4ad7b3dc61fe0d05d70b; UserNick=%E6%8C%BD%E5%BC%A6%E6%85%95%E7%AC%99; AU=D43; UN=m0_51276753; BT=1659591316703; p_uid=U010000; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_30853070190-1659503555660-810570!5744*1*m0_51276753; Hm_up_6bcd52f51e9b3dce32bec4a3997715ac=%7B%22islogin%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isonline%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isvip%22%3A%7B%22value%22%3A%220%22%2C%22scope%22%3A1%7D%2C%22uid_%22%3A%7B%22value%22%3A%22m0_51276753%22%2C%22scope%22%3A1%7D%7D; _ga=GA1.2.2095976161.1659759631; c_dl_um=-; c_dl_prid=1664636909019_381528; c_dl_rid=1664940903959_534359; c_dl_fref=https://www.csdn.net/; c_dl_fpage=/download/tza123/1154513; firstDie=1; __gpi=UID=0000082d9cdf8860:T=1659521211:RT=1664948965:S=ALNI_MZGWWFrlwFjWehXn2_-71S9SAvq4Q; csdn_highschool_close=close; __bid_n=183a6e9f41d79cb88c4207; historyList-new=%5B%22jsonpath%E6%89%93%E5%8D%B0False%22%2C%22jsonpath%E8%AF%BB%E5%8F%96json%E6%96%87%E4%BB%B6%22%2C%22%E7%88%AC%E8%99%AB%20gzip%22%2C%22%E7%88%AC%E8%99%ABoptions%22%2C%22zh-CN%20%E5%92%8C%20UTF-8%22%2C%22zh-CN%E7%BC%96%E7%A0%81%22%2C%22%E7%9F%A9%E9%98%B5%E5%BF%AB%E9%80%9F%E5%B9%82java%22%2C%22%E5%AE%89%E5%8D%93Webview%E6%89%8B%E6%8C%87%E7%BC%A9%E6%94%BE%22%2C%22Webview%E6%89%8B%E6%8C%87%E7%BC%A9%E6%94%BE%22%2C%22Webview%E7%BC%A9%E6%94%BE%22%5D; c_segment=10; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1664940842,1664948951,1664957846,1664968807; dc_sid=b64c993e3573b71603787e526b433bfc; _gid=GA1.2.1465745230.1664968807; FCNEC=%5B%5B%22AKsRol-kb8FiZifyo0jxZ-Kpnpzd_z3U3NLXnNmO6NyD7WwDHhgYex-lZ5tsogkEkItzjJKSqVDauTyt0KDKAaL5DC5DOLCUOmhsKWfKcxOCpg-806w3PPBOQChd-yMZNelkfvOVaKGYtgIuSv2_HSQjwHhpLcvPFg%3D%3D%22%5D%2Cnull%2C%5B%5D%5D; dc_session_id=10_1664972293654.105014; c_pref=default; c_first_ref=default; ssxmod_itna=iqGxgD9DyiSAKGHqiQr34=Q3GQ1DCWiDIxQ8g4GN3G8+DnqD=GFDK40EYSxi+AteSn+GYaeo1hh4aoY08E3sKWZlHvYDU4i8DCweFQrDeetD5xGoDPxDeDAGqGaDb4Dru5qGP9R6wv6ODWKDKx0kDY5DwPYkDYPDWxDFjr4xYiDkAxi7DD5DnaYvD4DWDWPkc6chxIAvmW5PW0KD9x0CDlady+ovBfdxMayL24i3f7FhP40OD0Kwuca+DBRkZ1y7eqnDfn2DrCco604oWD+xPi+KribdKn+gGl4Y1Dx4WAGPFlw/wtib7ZxxD===; ssxmod_itna2=iqGxgD9DyiSAKGHqiQr34=Q3GQ1DCWiDIxQ8xA6WfUxD/GADFx8UP97I7IeAIH/9=Lq0xqxhBPGFA2LqEtQMQkoa3G7bLgQpCgSpSbbcIFp89XAcFpQP7FLbToN0jwwZa18PcdXLsCrxoxbqPWWYreUy=d/51Vcxxo/T+qKBaDaya3GOOfre+22PQQ9gAnmPw58KNiRcEk2HEqO0mC2qPKiLGEodGL3cHESoYED07oDqPlAGYZA0Q3VRhclQord4DLxG7Tbi57jlhbribSgwxWN0RzAyUWqRyUi14SrRRRLvUYbGNenX5CXXPpMaU49+/=YbYD==; c_first_page=https%3A//blog.csdn.net/harvic880925/article/details/103193905%3Fops_request_misc%3D%25257B%252522request%25255Fid%252522%25253A%252522166497230016800180698814%252522%25252C%252522scm%252522%25253A%25252220140713.130102334..%252522%25257D%26request_id%3D166497230016800180698814%26biz_id%3D0%26utm_medium%3Ddistribute.pc_search_result.none-task-blog-2%7Eall%7Etop_positive%7Edefault-1-103193905-null-null.142%5Ev51%5Epc_rank_34_queryrelevant25%2C201%5Ev3%5Econtrol_2%26utm_term%3D%25E8%2580%2583%25E7%25A0%2594; c_dsid=11_1664972326760.312565; c_utm_medium=distribute.pc_search_result.none-task-blog-2%7Eall%7Etop_positive%7Edefault-1-103193905-null-null.142%5Ev51%5Epc_rank_34_queryrelevant25%2C201%5Ev3%5Econtrol_2; c_utm_term=%E8%80%83%E7%A0%94; c_page_id=default; c_ref=https%3A//www.csdn.net/; dc_tos=rja525; log_Id_pv=940; log_Id_view=3656; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1664972611; log_Id_click=1452',
    "user-agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36",
    "referer": "https://so.csdn.net/so/search?spm=1000.2115.3001.4498&q=%E8%80%83%E7%A0%94&t=&u=",
    "accept-encoding": "gzip, deflate",
    "accept-language": "zh-CN,zh;q=0.9",
    "accept": "application/json, text/plain, */*",
    "cache-control": "no-cache",
    "sec-ch-ua": '"Google Chrome";v="105", "Not)A;Brand";v="8", "Chromium";v="105"',
    "sec-ch-ua-mobile": "?0",
    "sec-ch-ua-platform": '\'"Windows\"',
    "sec-fetch-dest": "empty",
    "sec-fetch-mode": "cors",
    "sec-fetch-site": "same-origin",
    "pragma": "no-cache"
}

resp = requests.get(url=url, headers=headers)
resp.encoding = 'utf-8'

# with open("kaoyan_csdn.json", mode="w", encoding='utf-8') as f:
#     text = resp.text
#     f.write(text)
# print('over!')
# f.close()

text = json.loads(resp.text.replace("<em>", "").replace("</em>", ""))

articles = []
title = jsonpath.jsonpath(text, "$.result_vos[*].title")
article_url = jsonpath.jsonpath(text, "$.result_vos[*].url")
articleUrl = []
for i in article_url:
    articleUrl.append(i.split('?')[0])
description = jsonpath.jsonpath(text, "$.result_vos[*].description")
articles.append(title)
articles.append(articleUrl)
articles.append(description)
print(articles)

with open('kaoyan_csdn.csv', mode='w', encoding='utf-8') as f:
    for i in articles:
        csv.writer(f).writerow(i)
f.close()
