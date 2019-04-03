业务模块：
图片选择器，通过开源项目改造
开源地址：https://github.com/YancyYe/ImageSelector
// 使用方法

//    ImageConfig imageConfig
//     = new ImageConfig.Builder(
//     // FrescoImageLoader 可用自己用的缓存库
//     new FrescoImageLoader())
//     // 如果在 4.4 以上，则修改状态栏颜色 （默认黑色）
//     .steepToolBarColor(getResources().getColor(R.color.blue))
//     // 标题的背景颜色 （默认黑色）
//     .titleBgColor(getResources().getColor(R.color.blue))
//     // 提交按钮字体的颜色  （默认白色）
//     .titleSubmitTextColor(getResources().getColor(R.color.white))
//     // 标题颜色 （默认白色）
//     .titleTextColor(getResources().getColor(R.color.white))
//     // 开启多选   （默认为多选）  (单选 为 singleSelect)
//     .singleSelect()
//     .crop()
//     // 多选时的最大数量   （默认 9 张）
//     .mutiSelectMaxSize(9)
//     // 已选择的图片路径
//     .pathList(path)
//     // 拍照后存放的图片路径（默认 /temp/picture）
//     .filePath("/ImageSelector/Pictures")
//     // 开启拍照功能 （默认开启）
//     .showCamera()
//     .build();
//
//
//     ImageSelector.open(MainActivity.this, imageConfig);   // 开启图片选择器

    //接收方案
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == ImageSelector.IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
//            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
//
//            for (String path : pathList) {
//                Log.i("ImagePathList", path);
//            }
//
//            path.clear();
//            path.addAll(pathList);
//            adapter.notifyDataSetChanged();
//
//
//        }
//    }
