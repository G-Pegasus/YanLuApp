package com.tongji.yanluapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.tongji.yanluapp.bean.School
import com.tongji.yanluapp.databinding.FragmentInfoBinding
import com.tongji.yanluapp.ui.activity.SchoolInfoActivity
import com.tongji.yanluapp.ui.adapter.SchoolAdapter
import com.tongji.yanluapp.viewmodel.InfoViewModel
import me.hgj.jetpackmvvm.base.appContext
import me.hgj.jetpackmvvm.demo.app.base.BaseFragment1

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/14 16:25
 * @description:
 * @email: tongji0x208@gmail.com
 */
class InfoFragment : BaseFragment1<InfoViewModel, FragmentInfoBinding>() {

    private lateinit var schoolAdapter: SchoolAdapter

    override fun initView(savedInstanceState: Bundle?) {

        val tsinghua = School("清华大学",
            "https://bkimg.cdn.bcebos.com/pic/63d0f703918fa0ec08faacc53fde4eee3d6d54fb6deb?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "北京 / 985 / 软科1",
            "https://www.noobdream.com/schoolinfo/5/",
            "https://www.tsinghua.edu.cn/yjsy/"
        )

        val pku = School("北京大学",
            "https://bkimg.cdn.bcebos.com/pic/5ab5c9ea15ce36d3d53991f823ba2d87e950342a1be8?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "北京 / 985 / 软科2",
            "https://www.noobdream.com/schoolinfo/4/",
            "https://grs.pku.edu.cn/"
        )

        val fudan = School("复旦大学",
            "https://bkimg.cdn.bcebos.com/pic/eac4b74543a98226cffc1bda6bcbae014a90f703bdbf?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "上海 / 985 / 软科22",
            "https://www.noobdream.com/schoolinfo/14/",
            "https://gs.fudan.edu.cn/"
        )

        val whu = School("武汉大学",
            "https://bkimg.cdn.bcebos.com/pic/0b46f21fbe096b634c5ee7ef0c338744eaf8acce?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "武汉 / 985 / 软科14",
            "https://www.noobdream.com/schoolinfo/15/",
            "https://gs.whu.edu.cn/"
        )

        val sjtu = School("上海交通大学",
            "https://bkimg.cdn.bcebos.com/pic/42166d224f4a20a4fff87ceb9c529822720ed03c?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "上海 / 985 / 软科9",
            "https://www.noobdream.com/schoolinfo/16/",
            "https://www.gs.sjtu.edu.cn/"
        )

        val zju = School("浙江大学",
            "https://bkimg.cdn.bcebos.com/pic/728da9773912b31bb0513050ae53217adab44aed4b3c?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "杭州 / 985 / 软科3",
            "https://www.noobdream.com/schoolinfo/18/",
            "http://www.grs.zju.edu.cn/"
        )

        val ucas = School("中国科学院大学",
            "https://bkimg.cdn.bcebos.com/pic/0df3d7ca7bcb0a46162bebcb6163f6246b60af33?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "北京 / null / null",
            "https://www.noobdream.com/schoolinfo/77/",
            "https://www.ucas.ac.cn/"
        )

        val scu = School("四川大学",
            "https://bkimg.cdn.bcebos.com/pic/7aec54e736d12f2eb938f788b288c2628535e4ddc28a?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "成都 / 985 / 软科28",
            "https://www.noobdream.com/schoolinfo/13/",
            "https://gs.scu.edu.cn/"
        )

        val nju = School("南京大学",
            "https://bkimg.cdn.bcebos.com/pic/2fdda3cc7cd98d103d73086f2c3fb80e7aec90cc?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "南京 / 985 / 软科5",
            "https://www.noobdream.com/schoolinfo/17/",
            "https://grawww.nju.edu.cn/main.htm"
        )

        val nankai = School("南开大学",
            "https://bkimg.cdn.bcebos.com/pic/562c11dfa9ec8a13bb1224f6fb03918fa0ecc098?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "天津 / 985 / 软科26",
            "https://www.noobdream.com/schoolinfo/19/",
            "https://graduate.nankai.edu.cn/"
        )

        val buaa = School("北京航空航天大学",
            "https://bkimg.cdn.bcebos.com/pic/e850352ac65c103896bc6769b2119313b17e895f?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "北京 / 985 / 软科15",
            "https://www.noobdream.com/schoolinfo/20/",
            "http://graduate.buaa.edu.cn/s_y.htm"
        )

        val bnu = School("北京师范大学",
            "https://bkimg.cdn.bcebos.com/pic/8ad4b31c8701a18b87d69cf5de65100828381f30507c?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "北京 / 985 / 软科40",
            "https://www.noobdream.com/schoolinfo/21/",
            "https://yz.bnu.edu.cn/"
        )

        val tju = School("天津大学",
            "https://bkimg.cdn.bcebos.com/pic/58ee3d6d55fbb2fba9eb272c4d4a20a44723dcc8?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "天津 / 985 / 软科24",
            "noobdream.com/schoolinfo/22/",
            "http://yzb.tju.edu.cn/"
        )

        val hust = School("华中科技大学",
            "https://bkimg.cdn.bcebos.com/pic/32fa828ba61ea8d3fd1f4f721c43274e251f95caf226?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "武汉 / 985 / 软科10",
            "https://www.noobdream.com/schoolinfo/23/",
            "http://gs.hust.edu.cn/"
        )

        val bit = School("北京理工大学",
            "https://bkimg.cdn.bcebos.com/pic/6159252dd42a2834e96c6a2156b5c9ea14cebfd6?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "北京 / 985 / 软科10",
            "https://www.noobdream.com/schoolinfo/24/",
            "https://grd.bit.edu.cn/"
        )

        val seu = School("东南大学",
            "https://bkimg.cdn.bcebos.com/pic/cefc1e178a82b9010534459b728da9773812eff9?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "南京 / 985 / 软科21",
            "https://www.noobdream.com/schoolinfo/25/",
            "https://seugs.seu.edu.cn/"
        )

        val sysu = School("中山大学",
            "https://bkimg.cdn.bcebos.com/pic/b3119313b07eca8032bb094b9a2397dda04483db?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "广州 / 985 / 软科31",
            "https://www.noobdream.com/schoolinfo/26/",
            "https://graduate.sysu.edu.cn/page/25"
        )

        val ecnu = School("华东师范大学",
            "https://bkimg.cdn.bcebos.com/pic/c995d143ad4bd113255914405bafa40f4bfb052f?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "上海 / 985 / 软科48",
            "https://www.noobdream.com/schoolinfo/27/",
            "http://www.yjsy.ecnu.edu.cn/"
        )

        val hit = School("哈尔滨工业大学",
            "https://bkimg.cdn.bcebos.com/pic/0e2442a7d933c8952b5646f3d41373f08202000a?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "哈尔滨 / 985 / 软科4",
            "https://www.noobdream.com/schoolinfo/28/",
            "http://hitgs.hit.edu.cn/"
        )

        val xmu = School("厦门大学",
            "https://bkimg.cdn.bcebos.com/pic/267f9e2f070828381f30933f3ed4be014c086e065bc0?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "厦门 / 985 / 软科44",
            "https://www.noobdream.com/schoolinfo/30/",
            "https://gs.xmu.edu.cn/"
        )

        val nwpu = School("西北工业大学",
            "https://bkimg.cdn.bcebos.com/pic/d50735fae6cd7b89652eefd9062442a7d9330e2e?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "西安 / 985 / 软科13",
            "https://www.noobdream.com/schoolinfo/31/",
            "https://grs.xatu.edu.cn/"
        )

        val csu = School("中南大学",
            "https://bkimg.cdn.bcebos.com/pic/1ad5ad6eddc451dab4579a4db6fd5266d11632f6?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "长沙 / 985 / 软科19",
            "https://www.noobdream.com/schoolinfo/33/",
            "https://gra.csu.edu.cn/"
        )

        val dlut = School("大连理工大学",
            "https://bkimg.cdn.bcebos.com/pic/8694a4c27d1ed21b0ef47efbf83ecac451da81cb91d7?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "大连 / 985 / 软科27",
            "https://www.noobdream.com/schoolinfo/34/",
            "http://gs.dlut.edu.cn/"
        )

        val uestc = School("电子科技大学",
            "https://bkimg.cdn.bcebos.com/pic/2f738bd4b31c8701a18b3b071935892f0708293851b5?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "成都 / 985 / 软科7",
            "https://www.noobdream.com/schoolinfo/35/",
            "https://gr.uestc.edu.cn/"
        )

        val scut = School("华南理工大学",
            "https://bkimg.cdn.bcebos.com/pic/d52a2834349b033b092bcb2d14ce36d3d439bda1?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "广州 / 985 / 软科19",
            "https://www.noobdream.com/schoolinfo/36/",
            "https://yz.scut.edu.cn/"
        )

        val jlu = School("吉林大学",
            "https://bkimg.cdn.bcebos.com/pic/6a600c338744ebf8226d5703d4f9d72a6059a7e9?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "长春 / 985 / 软科17",
            "https://www.noobdream.com/schoolinfo/37/",
            "https://yjsy.jlu.edu.cn/"
        )

        val hnu = School("湖南大学",
            "https://bkimg.cdn.bcebos.com/pic/2e2eb9389b504fc20964271ae2dde71190ef6df2?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "长沙 / 985 / 软科33",
            "https://www.noobdream.com/schoolinfo/38/",
            "http://gra.hnu.edu.cn/"
        )

        val cqu = School("重庆大学",
            "https://bkimg.cdn.bcebos.com/pic/4bed2e738bd4b31c3ccbe9d888d6277f9f2ff8da?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "重庆 / 985 / 软科37",
            "https://www.noobdream.com/schoolinfo/39/",
            "http://graduate.cqu.edu.cn/"
        )

        val cau = School("中国农业大学",
            "https://bkimg.cdn.bcebos.com/pic/f9dcd100baa1cd11fa247bd8b812c8fcc2ce2db3?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "北京市 / 985 / 软科35",
            "https://www.noobdream.com/schoolinfo/43/",
            "http://gradsch.cau.edu.cn/homepage/index.do"
        )

        val neu = School("东北大学",
            "https://bkimg.cdn.bcebos.com/pic/b3b7d0a20cf431adcbef61b2097cbbaf2edda2cc32ac?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "沈阳 / 985 / 软科23",
            "https://www.noobdream.com/schoolinfo/47/",
            "http://www.graduate.neu.edu.cn/"
        )

        val lzu = School("兰州大学",
            "https://bkimg.cdn.bcebos.com/pic/9f510fb30f2442a76324e122d443ad4bd113020a?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "兰州 / 985 / 软科53",
            "https://www.noobdream.com/schoolinfo/48/",
            "https://yz.lzu.edu.cn/"
        )

        val ruc = School("中国人民大学",
            "https://bkimg.cdn.bcebos.com/pic/7e3e6709c93d70cfe25242c6f1dcd100bba12b55?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "北京 / 985 / 软科25",
            "https://www.noobdream.com/schoolinfo/58/",
            "https://grs.ruc.edu.cn/"
        )

        val muc = School("中央民族大学",
            "https://bkimg.cdn.bcebos.com/pic/7aec54e736d12f2eb938be580b88c2628535e5ddc2fb?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "北京 / 985 / 软科99",
            "https://www.noobdream.com/schoolinfo/70/",
            "https://grs.muc.edu.cn/"
        )

        val nudt = School("国防科技大学",
            "https://bkimg.cdn.bcebos.com/pic/3b292df5e0fe9925bc31773261e249df8db1cb13db35?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "长沙 / 985 / null",
            "https://www.noobdream.com/schoolinfo/105/",
            "http://yjszs.nudt.edu.cn/index/index.view"
        )

        val tongji = School("同济大学",
            "https://bkimg.cdn.bcebos.com/pic/6a63f6246b600c3341fb9ce4164c510fd9f9a118?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "上海 / 985 / 软科8",
            "https://www.noobdream.com/schoolinfo/113/",
            "https://gs.tongji.edu.cn/"
        )

        val sdu = School("山东大学",
            "https://bkimg.cdn.bcebos.com/pic/58ee3d6d55fbb2fb43168804770037a4462309f776d8?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "济南 / 985 / 软科33",
            "https://www.noobdream.com/schoolinfo/140/",
            "https://www.grad.sdu.edu.cn/"
        )

        val ouc = School("中国海洋大学",
            "https://bkimg.cdn.bcebos.com/pic/5243fbf2b2119313cfd029a566380cd791238d10?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "青岛 / 985 / 软科63",
            "https://www.noobdream.com/schoolinfo/141/",
            "http://yz.ouc.edu.cn/main.htm"
        )

        val ustc = School("中国科学技术大学",
            "https://bkimg.cdn.bcebos.com/pic/42166d224f4a20a44623f89da0188f22720e0cf37a1c?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "合肥 / 985 / 软科5",
            "https://www.noobdream.com/schoolinfo/202/",
            "https://yz.ustc.edu.cn/"
        )

        val nwsuaf = School("西北农林科技大学",
            "https://bkimg.cdn.bcebos.com/pic/960a304e251f95cad1c8fb618a5d683e6709c83df498?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "西安 / 985 / 软科162",
            "https://www.noobdream.com/schoolinfo/244/",
            "https://yz.nwsuaf.edu.cn/"
        )

        val xjtu = School("西安交通大学",
            "https://bkimg.cdn.bcebos.com/pic/e1fe9925bc315c602230288382b1cb1349547730?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg",
            "西安 / 985 / 软科12",
            "https://www.noobdream.com/schoolinfo/281/",
            "https://yz.xjtu.edu.cn/"
        )

        val cqupt = School("重庆邮电大学",
            "https://bkimg.cdn.bcebos.com/pic/3c6d55fbb2fb43163d37add525a4462309f7d371?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto",
            "重庆 / 双非 / 软科57",
            "https://www.noobdream.com/schoolinfo/110/",
            ""
        )
        val njupt = School("南京邮电大学",
            "https://bkimg.cdn.bcebos.com/pic/0b55b319ebc4b74543a92e02f7b609178a82b901bfc7?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxNTA=,g_7,xp_5,yp_5/format,f_auto",
            "南京 / 双一流 / 软科51",
            "https://www.noobdream.com/schoolinfo/161/",
            ""
        )
        val bupt = School("北京邮电大学",
            "https://bkimg.cdn.bcebos.com/pic/aec379310a55b319c46a7d6941a98226cffc171e?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto",
            "北京 / 211 / 软科16",
            "https://www.noobdream.com/schoolinfo/29/",
            ""
        )

        val schoolList = mutableListOf<School>()
        schoolList.add(tsinghua)
        schoolList.add(pku)
        schoolList.add(fudan)
        schoolList.add(whu)
        schoolList.add(sjtu)
        schoolList.add(zju)
        schoolList.add(ucas)
        schoolList.add(scu)
        schoolList.add(nju)
        schoolList.add(nankai)
        schoolList.add(buaa)
        schoolList.add(bnu)
        schoolList.add(tju)
        schoolList.add(hust)
        schoolList.add(bit)
        schoolList.add(seu)
        schoolList.add(sysu)
        schoolList.add(ecnu)
        schoolList.add(hit)
        schoolList.add(xmu)
        schoolList.add(nwpu)
        schoolList.add(csu)
        schoolList.add(dlut)
        schoolList.add(uestc)
        schoolList.add(scut)
        schoolList.add(jlu)
        schoolList.add(hnu)
        schoolList.add(cqu)
        schoolList.add(cau)
        schoolList.add(neu)
        schoolList.add(lzu)
        schoolList.add(ruc)
        schoolList.add(muc)
        schoolList.add(nudt)
        schoolList.add(tongji)
        schoolList.add(sdu)
        schoolList.add(ouc)
        schoolList.add(ustc)
        schoolList.add(nwsuaf)
        schoolList.add(xjtu)
        schoolList.add(cqupt)
        schoolList.add(njupt)
        schoolList.add(bupt)

        val rvSchool = mViewBind.rvSchool
        rvSchool.layoutManager = LinearLayoutManager(appContext)
        schoolAdapter = SchoolAdapter(requireContext(), schoolList)
        rvSchool.adapter = schoolAdapter
        schoolAdapter.setOnItemClickListener(object : SchoolAdapter.OnItemClickListener {

            override fun onItemClick(view: View, position: Int) {
                val intent = Intent(context, SchoolInfoActivity::class.java)
                val bundle = Bundle()
                val schoolWeb = schoolList[position].schoolInfo
                bundle.putString("data", schoolWeb)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onItemLongClick(view: View, position: Int) {

            }
        })
    }

    override fun dismissLoading() {

    }

    override fun showLoading(message: String) {

    }
}