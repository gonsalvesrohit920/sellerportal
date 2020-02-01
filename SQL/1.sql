-- Exported from QuickDBD: https://www.quickdatabasediagrams.com/
-- Link to schema: https://app.quickdatabasediagrams.com/#/d/sSDoy3
-- NOTE! If you have used non-SQL datatypes in your design, you will have to change these here.


CREATE TABLE "Seller" (
    "s_id" SERIAL   NOT NULL,
    "s_name" varchar(50)   NOT NULL,
    "email" varchar(50)   NOT NULL,
    "s_password" varchar(50)   NOT NULL,
    "phone_no" varchar(10)   NOT NULL,
    "street" varchar(30)   NOT NULL,
    "city" varchar(20)   NOT NULL,
    "pincode" int   NOT NULL,
    "application_status" varchar(10)   NOT NULL,
    CONSTRAINT "pk_Seller" PRIMARY KEY (
        "s_id"
     ),
    CONSTRAINT "uc_Seller_email" UNIQUE (
        "email"
    )
);

CREATE TABLE "Documents" (
    "s_id" int   NOT NULL,
    "pan_no" varchar(10)   NOT NULL,
    "pan_image_type" varchar(20)   NOT NULL,
    "pan_image" bytea   NOT NULL,
    "gst_in_no" varchar(16)   NOT NULL,
    "gst_in_image_type" varchar(20)   NOT NULL,
    "gst_in_image" bytea   NOT NULL
);

CREATE TABLE "Product" (
    "p_id" SERIAL   NOT NULL,
    "s_id" int   NOT NULL,
    "p_name" varchar(30)   NOT NULL,
    "p_description" varchar(30)   NOT NULL,
    "category" int   NOT NULL,
    "quantity" int   NOT NULL,
    "price" int   NOT NULL,
    CONSTRAINT "pk_Product" PRIMARY KEY (
        "p_id"
     )
);

CREATE TABLE "Product_images" (
    "p_id" int   NOT NULL,
    "p_image_type" varchar(10)   NOT NULL,
    "image_file" bytea   NOT NULL
);

CREATE TABLE "Category" (
    "cat_id" SERIAL   NOT NULL,
    "cat_name" varchar(10)   NOT NULL,
    CONSTRAINT "pk_Category" PRIMARY KEY (
        "cat_id"
     ),
    CONSTRAINT "uc_Category_cat_name" UNIQUE (
        "cat_name"
    )
);

CREATE TABLE "Category_questions" (
    "catq_id" SERIAL   NOT NULL,
    "cat_id" int   NOT NULL,
    "cat_question" varchar(10)   NOT NULL,
    "cat_question_type" varchar(10)   NOT NULL,
    CONSTRAINT "pk_Category_questions" PRIMARY KEY (
        "catq_id"
     )
);

CREATE TABLE "Category_questions_options" (
    "catq_id" int   NOT NULL,
    "catq_option" varchar(40)   NOT NULL
);

CREATE TABLE "Category_answers" (
    "p_id" int   NOT NULL,
    "cat_id" int   NOT NULL,
    "catq_id" int   NOT NULL,
    "cat_answer" varchar(30)   NOT NULL,
    CONSTRAINT "pk_Category_answers" PRIMARY KEY (
        "p_id","cat_id","catq_id"
     )
);

ALTER TABLE "Documents" ADD CONSTRAINT "fk_Documents_s_id" FOREIGN KEY("s_id")
REFERENCES "Seller" ("s_id");

ALTER TABLE "Product" ADD CONSTRAINT "fk_Product_s_id" FOREIGN KEY("s_id")
REFERENCES "Seller" ("s_id");

ALTER TABLE "Product" ADD CONSTRAINT "fk_Product_category" FOREIGN KEY("category")
REFERENCES "Category" ("cat_id");

ALTER TABLE "Product_images" ADD CONSTRAINT "fk_Product_images_p_id" FOREIGN KEY("p_id")
REFERENCES "Product" ("p_id");

ALTER TABLE "Category_questions" ADD CONSTRAINT "fk_Category_questions_cat_id" FOREIGN KEY("cat_id")
REFERENCES "Category" ("cat_id");

ALTER TABLE "Category_questions_options" ADD CONSTRAINT "fk_Category_questions_options_catq_id" FOREIGN KEY("catq_id")
REFERENCES "Category_questions" ("catq_id");

ALTER TABLE "Category_answers" ADD CONSTRAINT "fk_Category_answers_p_id" FOREIGN KEY("p_id")
REFERENCES "Product" ("p_id");

ALTER TABLE "Category_answers" ADD CONSTRAINT "fk_Category_answers_cat_id" FOREIGN KEY("cat_id")
REFERENCES "Category" ("cat_id");

ALTER TABLE "Category_answers" ADD CONSTRAINT "fk_Category_answers_catq_id" FOREIGN KEY("catq_id")
REFERENCES "Category_questions" ("catq_id");

