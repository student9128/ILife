package com.kevin.live.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Kevin on 2018/8/14<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>
 */
public class CookBookDetailBean implements Parcelable {


    private String msg;
    private ResultBean result;
    private String retCode;

    public CookBookDetailBean() {

    }

    protected CookBookDetailBean(Parcel in) {
        msg = in.readString();
        retCode = in.readString();
    }

    public static final Creator<CookBookDetailBean> CREATOR = new Creator<CookBookDetailBean>() {
        @Override
        public CookBookDetailBean createFromParcel(Parcel in) {
            return new CookBookDetailBean(in);
        }

        @Override
        public CookBookDetailBean[] newArray(int size) {
            return new CookBookDetailBean[size];
        }
    };

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(msg);
        dest.writeString(retCode);
    }

    public static class ResultBean implements Parcelable{

        private int curPage;
        private int total;
        private List<ListBean> list;

        public ResultBean() {
        }

        protected ResultBean(Parcel in) {
            curPage = in.readInt();
            total = in.readInt();
            list = in.createTypedArrayList(ListBean.CREATOR);
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(curPage);
            dest.writeInt(total);
            dest.writeTypedList(list);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<ResultBean> CREATOR = new Creator<ResultBean>() {
            @Override
            public ResultBean createFromParcel(Parcel in) {
                return new ResultBean(in);
            }

            @Override
            public ResultBean[] newArray(int size) {
                return new ResultBean[size];
            }
        };

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Parcelable{


            private String ctgTitles;
            private String menuId;
            private String name;
            private RecipeBean recipe;
            private String thumbnail;
            private List<String> ctgIds;

            public ListBean() {
            }

            protected ListBean(Parcel in) {
                ctgTitles = in.readString();
                menuId = in.readString();
                name = in.readString();
                recipe = in.readParcelable(RecipeBean.class.getClassLoader());
                thumbnail = in.readString();
                ctgIds = in.createStringArrayList();
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(ctgTitles);
                dest.writeString(menuId);
                dest.writeString(name);
                dest.writeParcelable(recipe, flags);
                dest.writeString(thumbnail);
                dest.writeStringList(ctgIds);
            }

            @Override
            public int describeContents() {
                return 0;
            }

            public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
                @Override
                public ListBean createFromParcel(Parcel in) {
                    return new ListBean(in);
                }

                @Override
                public ListBean[] newArray(int size) {
                    return new ListBean[size];
                }
            };

            public String getCtgTitles() {
                return ctgTitles;
            }

            public void setCtgTitles(String ctgTitles) {
                this.ctgTitles = ctgTitles;
            }

            public String getMenuId() {
                return menuId;
            }

            public void setMenuId(String menuId) {
                this.menuId = menuId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public RecipeBean getRecipe() {
                return recipe;
            }

            public void setRecipe(RecipeBean recipe) {
                this.recipe = recipe;
            }

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public List<String> getCtgIds() {
                return ctgIds;
            }

            public void setCtgIds(List<String> ctgIds) {
                this.ctgIds = ctgIds;
            }

            public static class RecipeBean implements Parcelable{


                private String img;
                private String ingredients;
                private String method;
                private String sumary;
                private String title;

                public RecipeBean() {
                }

                protected RecipeBean(Parcel in) {
                    img = in.readString();
                    ingredients = in.readString();
                    method = in.readString();
                    sumary = in.readString();
                    title = in.readString();
                }

                public static final Creator<RecipeBean> CREATOR = new Creator<RecipeBean>() {
                    @Override
                    public RecipeBean createFromParcel(Parcel in) {
                        return new RecipeBean(in);
                    }

                    @Override
                    public RecipeBean[] newArray(int size) {
                        return new RecipeBean[size];
                    }
                };

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getIngredients() {
                    return ingredients;
                }

                public void setIngredients(String ingredients) {
                    this.ingredients = ingredients;
                }

                public String getMethod() {
                    return method;
                }

                public void setMethod(String method) {
                    this.method = method;
                }

                public String getSumary() {
                    return sumary;
                }

                public void setSumary(String sumary) {
                    this.sumary = sumary;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(img);
                    dest.writeString(ingredients);
                    dest.writeString(method);
                    dest.writeString(sumary);
                    dest.writeString(title);
                }
            }
        }
    }
}
