package com.tongji.yanluapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tongji.yanluapp.app.data.SchoolData.ahu
import com.tongji.yanluapp.app.data.SchoolData.bit
import com.tongji.yanluapp.app.data.SchoolData.bjfu
import com.tongji.yanluapp.app.data.SchoolData.bjtu
import com.tongji.yanluapp.app.data.SchoolData.bnu
import com.tongji.yanluapp.app.data.SchoolData.buaa
import com.tongji.yanluapp.app.data.SchoolData.buct
import com.tongji.yanluapp.app.data.SchoolData.bupt
import com.tongji.yanluapp.app.data.SchoolData.cau
import com.tongji.yanluapp.app.data.SchoolData.ccnu
import com.tongji.yanluapp.app.data.SchoolData.chd
import com.tongji.yanluapp.app.data.SchoolData.cqu
import com.tongji.yanluapp.app.data.SchoolData.cqupt
import com.tongji.yanluapp.app.data.SchoolData.csu
import com.tongji.yanluapp.app.data.SchoolData.cuc
import com.tongji.yanluapp.app.data.SchoolData.cug
import com.tongji.yanluapp.app.data.SchoolData.cumt
import com.tongji.yanluapp.app.data.SchoolData.cup
import com.tongji.yanluapp.app.data.SchoolData.cust
import com.tongji.yanluapp.app.data.SchoolData.dhu
import com.tongji.yanluapp.app.data.SchoolData.dlmu
import com.tongji.yanluapp.app.data.SchoolData.dlut
import com.tongji.yanluapp.app.data.SchoolData.ecnu
import com.tongji.yanluapp.app.data.SchoolData.ecust
import com.tongji.yanluapp.app.data.SchoolData.fudan
import com.tongji.yanluapp.app.data.SchoolData.fzu
import com.tongji.yanluapp.app.data.SchoolData.gdut
import com.tongji.yanluapp.app.data.SchoolData.guet
import com.tongji.yanluapp.app.data.SchoolData.gxu
import com.tongji.yanluapp.app.data.SchoolData.gzu
import com.tongji.yanluapp.app.data.SchoolData.hainanu
import com.tongji.yanluapp.app.data.SchoolData.hdu
import com.tongji.yanluapp.app.data.SchoolData.hebut
import com.tongji.yanluapp.app.data.SchoolData.hfut
import com.tongji.yanluapp.app.data.SchoolData.hhu
import com.tongji.yanluapp.app.data.SchoolData.hit
import com.tongji.yanluapp.app.data.SchoolData.hnu
import com.tongji.yanluapp.app.data.SchoolData.hrbeu
import com.tongji.yanluapp.app.data.SchoolData.hunnu
import com.tongji.yanluapp.app.data.SchoolData.hust
import com.tongji.yanluapp.app.data.SchoolData.hzau
import com.tongji.yanluapp.app.data.SchoolData.hznu
import com.tongji.yanluapp.app.data.SchoolData.imu
import com.tongji.yanluapp.app.data.SchoolData.jiangnan
import com.tongji.yanluapp.app.data.SchoolData.jlu
import com.tongji.yanluapp.app.data.SchoolData.jnu
import com.tongji.yanluapp.app.data.SchoolData.lnu
import com.tongji.yanluapp.app.data.SchoolData.lzu
import com.tongji.yanluapp.app.data.SchoolData.muc
import com.tongji.yanluapp.app.data.SchoolData.nankai
import com.tongji.yanluapp.app.data.SchoolData.ncepu
import com.tongji.yanluapp.app.data.SchoolData.ncu
import com.tongji.yanluapp.app.data.SchoolData.ncut
import com.tongji.yanluapp.app.data.SchoolData.nenu
import com.tongji.yanluapp.app.data.SchoolData.neu
import com.tongji.yanluapp.app.data.SchoolData.njau
import com.tongji.yanluapp.app.data.SchoolData.njnu
import com.tongji.yanluapp.app.data.SchoolData.nju
import com.tongji.yanluapp.app.data.SchoolData.njupt
import com.tongji.yanluapp.app.data.SchoolData.njust
import com.tongji.yanluapp.app.data.SchoolData.nuaa
import com.tongji.yanluapp.app.data.SchoolData.nudt
import com.tongji.yanluapp.app.data.SchoolData.nwpu
import com.tongji.yanluapp.app.data.SchoolData.nwsuaf
import com.tongji.yanluapp.app.data.SchoolData.nwu
import com.tongji.yanluapp.app.data.SchoolData.nxu
import com.tongji.yanluapp.app.data.SchoolData.ouc
import com.tongji.yanluapp.app.data.SchoolData.pku
import com.tongji.yanluapp.app.data.SchoolData.qhu
import com.tongji.yanluapp.app.data.SchoolData.ruc
import com.tongji.yanluapp.app.data.SchoolData.scnu
import com.tongji.yanluapp.app.data.SchoolData.scu
import com.tongji.yanluapp.app.data.SchoolData.scut
import com.tongji.yanluapp.app.data.SchoolData.sdu
import com.tongji.yanluapp.app.data.SchoolData.sdust
import com.tongji.yanluapp.app.data.SchoolData.seu
import com.tongji.yanluapp.app.data.SchoolData.shu
import com.tongji.yanluapp.app.data.SchoolData.shzu
import com.tongji.yanluapp.app.data.SchoolData.sjtu
import com.tongji.yanluapp.app.data.SchoolData.snnu
import com.tongji.yanluapp.app.data.SchoolData.suda
import com.tongji.yanluapp.app.data.SchoolData.swjtu
import com.tongji.yanluapp.app.data.SchoolData.swu
import com.tongji.yanluapp.app.data.SchoolData.swufe
import com.tongji.yanluapp.app.data.SchoolData.sxu
import com.tongji.yanluapp.app.data.SchoolData.sysu
import com.tongji.yanluapp.app.data.SchoolData.szu
import com.tongji.yanluapp.app.data.SchoolData.tju
import com.tongji.yanluapp.app.data.SchoolData.tjut
import com.tongji.yanluapp.app.data.SchoolData.tongji
import com.tongji.yanluapp.app.data.SchoolData.tsinghua
import com.tongji.yanluapp.app.data.SchoolData.tyut
import com.tongji.yanluapp.app.data.SchoolData.ucas
import com.tongji.yanluapp.app.data.SchoolData.uestc
import com.tongji.yanluapp.app.data.SchoolData.ujs
import com.tongji.yanluapp.app.data.SchoolData.ustb
import com.tongji.yanluapp.app.data.SchoolData.ustc
import com.tongji.yanluapp.app.data.SchoolData.utibet
import com.tongji.yanluapp.app.data.SchoolData.whu
import com.tongji.yanluapp.app.data.SchoolData.whut
import com.tongji.yanluapp.app.data.SchoolData.wit
import com.tongji.yanluapp.app.data.SchoolData.wust
import com.tongji.yanluapp.app.data.SchoolData.xidian
import com.tongji.yanluapp.app.data.SchoolData.xjtu
import com.tongji.yanluapp.app.data.SchoolData.xju
import com.tongji.yanluapp.app.data.SchoolData.xmu
import com.tongji.yanluapp.app.data.SchoolData.xtu
import com.tongji.yanluapp.app.data.SchoolData.xupt
import com.tongji.yanluapp.app.data.SchoolData.ybu
import com.tongji.yanluapp.app.data.SchoolData.ynu
import com.tongji.yanluapp.app.data.SchoolData.ysu
import com.tongji.yanluapp.app.data.SchoolData.zju
import com.tongji.yanluapp.app.data.SchoolData.zjut
import com.tongji.yanluapp.app.data.SchoolData.zuel
import com.tongji.yanluapp.app.data.SchoolData.zzu
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
    private val viewModel by lazy { ViewModelProvider(this)[InfoViewModel::class.java] }
    private val schoolList = mutableListOf<School>()

    override fun initView(savedInstanceState: Bundle?) {
        addList()
        viewModel.listData.addAll(schoolList)

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

    private fun addList() {
        // 985
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

        // 211
        schoolList.add(xidian)
        schoolList.add(bupt)
        schoolList.add(ustb)
        schoolList.add(swu)
        schoolList.add(swufe)
        schoolList.add(ecust)
        schoolList.add(ccnu)
        schoolList.add(jnu)
        schoolList.add(suda)
        schoolList.add(jiangnan)
        schoolList.add(swjtu)
        schoolList.add(shu)
        schoolList.add(nwu)
        schoolList.add(fzu)
        schoolList.add(zzu)
        schoolList.add(nxu)
        schoolList.add(cuc)
        schoolList.add(bjfu)
        schoolList.add(buct)
        schoolList.add(bjtu)
        schoolList.add(hainanu)
        schoolList.add(qhu)
        schoolList.add(imu)
        schoolList.add(utibet)
        schoolList.add(shzu)
        schoolList.add(xju)
        schoolList.add(gzu)
        schoolList.add(gxu)
        schoolList.add(ynu)
        schoolList.add(ncepu)
        schoolList.add(cumt)
        schoolList.add(cup)
        schoolList.add(cug)
        schoolList.add(whut)
        schoolList.add(hzau)
        schoolList.add(zuel)
        schoolList.add(hunnu)
        schoolList.add(dhu)
        schoolList.add(scnu)
        schoolList.add(nuaa)
        schoolList.add(njust)
        schoolList.add(hhu)
        schoolList.add(njau)
        schoolList.add(njnu)
        schoolList.add(lnu)
        schoolList.add(dlmu)
        schoolList.add(ybu)
        schoolList.add(ahu)
        schoolList.add(hfut)
        schoolList.add(hebut)
        schoolList.add(tyut)
        schoolList.add(chd)
        schoolList.add(snnu)
        schoolList.add(ncu)
        schoolList.add(hrbeu)
        schoolList.add(nenu)

        // 双非
        schoolList.add(njupt)
        schoolList.add(cqupt)
        schoolList.add(hdu)
        schoolList.add(ncut)
        schoolList.add(ysu)
        schoolList.add(sxu)
        schoolList.add(wit)
        schoolList.add(guet)
        schoolList.add(xupt)
        schoolList.add(gdut)
        schoolList.add(sdust)
        schoolList.add(xtu)
        schoolList.add(szu)
        schoolList.add(zjut)
        schoolList.add(ujs)
        schoolList.add(wust)
        schoolList.add(hznu)
        schoolList.add(tjut)
        schoolList.add(cust)
    }
}