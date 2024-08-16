import React, { useCallback, useEffect, useState } from "react";
import { FoodApi, validUser } from "../utils";
import { useNavigate } from "react-router-dom";
import { Category, Container, FoodMenuCard, SubFooter } from "../Components";
import Swal from "sweetalert2";
import MenuBody from "../Components/MenuBody";
import { useSelector } from "react-redux";

const Menu = () => {
  const [foodItems, setFoodItems] = useState([]);
  const [filteredFoodItems, setFilteredFoodItems] = useState([]);
  const [categories, setCategories] = useState([]);
  const [selectedCats, setSelectedCat] = useState({
    id: 111,
    name: "All Categories",
    desc: "Choose food from all Categories.",
  });

  const [diet, setDiet] = useState(0);

  const items = useSelector((state) => state.cartItems);
  const nav = useNavigate();

  useEffect(() => {
    const valid = validUser();
    if (!valid) {
      nav("/login");
      Swal.fire({
        title: "Authentication Required",
        text: "Please login to continue.",
        icon: "warning",
        confirmButtonText: "OK",
      });
      return;
    }

    const fetchData = async () => {
      const data = await FoodApi.fetchFoodData("/food/all");

      if (data.status == 200) {
        const foodItems = data.items;
        setFoodItems(foodItems);
        setFilteredFoodItems(foodItems);
      } else {
        const status = data.response?.status;
        if (status == 401) {
          nav("/login");
        }
      }
    };

    const fetchCategories = async () => {
      const data = await FoodApi.fetchFoodData("/food/categories");

      if (data.status == 200) {
        const cats = data.items;
        setCategories(cats);
        //console.log(categories);
      }
    };

    fetchCategories();

    fetchData();

    return () => {
      const jsonString = JSON.stringify(items);
      const encodedString = encodeURIComponent(jsonString);
      // document.cookie = `myObject=${encodedString}; max-age=${
      //   7 * 24 * 60 * 60
      // }; path=/`;

      localStorage.setItem("cart", jsonString);
    };
  }, []);

  // TO filter based on category
  const selectCategory = (cat, diet1) => {
    diet1 = diet1 ? diet1 : 0;

    if (cat == 111) {
      if (diet1 == 0) {
        setDiet(0);
        setFilteredFoodItems(foodItems);
      } else {
        const filterFoodList = foodItems.filter((food) => food.diet == diet1);
        setFilteredFoodItems(filterFoodList);
      }
      setSelectedCat({
        id: 111,
        name: "All Dishes",
        desc: "Choose food from all Categories.",
      });
    } else if (cat == 222) {
      if (diet1 == 0) {
        setDiet(0);
        const filteredList = foodItems.filter((item) => item.featured);
        setFilteredFoodItems(filteredList);
      } else {
        const filteredList = foodItems.filter(
          (item) => item.featured && item.diet == diet1
        );
        setFilteredFoodItems(filteredList);
      }

      setSelectedCat({
        id: 222,
        name: "Featured Dishes",
        desc: "Enjoy our featured dishes.",
      });
    } else {
      if (diet1 == 0) {
        setDiet(0);
        const catogory = categories.find((item) => item.id == cat);

        setSelectedCat(catogory);
        const filterFoodList = foodItems.filter((food) => food.category == cat);

        setFilteredFoodItems(filterFoodList);
      } else {
        const catogory = categories.find((item) => item.id == cat);

        setSelectedCat(catogory);
        const filterFoodList = foodItems.filter(
          (food) => food.category == cat && food.diet == diet1
        );

        setFilteredFoodItems(filterFoodList);
      }
    }
  };

  const handleDiet = (cat) => {
    if (cat == 0 && diet != 0) {
      setDiet(0);
      selectCategory(selectedCats.id, cat);
    } else if (diet == cat) {
      setDiet(0);

      selectCategory(selectedCats.id, cat);
    } else {
      setDiet(cat);
      selectCategory(selectedCats.id, cat);
    }
  };

  return (
    <div className="pt-10 pb-8">
      <Container
        className={"flex-col items-start md:justify-between md:items-center"}
      >
        <div className="md:w-[80%] pl-2">
          <h3 className="text-2xl font-semibold">{selectedCats.name}</h3>
          <p>{selectedCats.desc}</p>
        </div>
        <div className="md:w-[80%] flex justify-between flex-wrap mt-5">
          <div className="cursor-pointer" onClick={() => selectCategory(111)}>
            <Category name={"All"} selectId={selectedCats.id} id={111} />
          </div>
          <div className="cursor-pointer" onClick={() => selectCategory(222)}>
            <Category name={"Featured"} selectId={selectedCats.id} id={222} />
          </div>

          {categories.map((cat) => (
            <div
              className="cursor-pointer"
              key={cat.id}
              onClick={() => selectCategory(cat.id)}
            >
              <Category {...cat} selectId={selectedCats.id} />
            </div>
          ))}
        </div>
        <hr className="w-full mt-8 border-t shadow-hr-dark" />
        <Container className={"mt-8"}>
          <div className="flex gap-10">
            <button
              onClick={() => handleDiet(0)}
              className={`border-2 border-primery px-4 py-2 text-xl rounded-lg 
              ${
                diet == 0
                  ? " bg-primery text-slate-50 hover:bg-slate-50 hover:text-primery"
                  : " hover:bg-primery hover:text-slate-50"
              }
            `}
            >
              All
            </button>
            <button
              onClick={() => handleDiet(1)}
              className={`border-2 border-primery px-4 py-2 text-xl rounded-lg 
              ${
                diet == 1
                  ? " bg-primery text-slate-50 hover:bg-slate-50 hover:text-primery"
                  : " hover:bg-primery hover:text-slate-50"
              }
            `}
            >
              Veg
            </button>
            <button
              onClick={() => handleDiet(2)}
              className={`border-2 border-primery px-4 py-2 text-xl rounded-lg 
              ${
                diet == 2
                  ? " bg-primery text-slate-50 hover:bg-slate-50 hover:text-primery"
                  : " hover:bg-primery hover:text-slate-50"
              }
            `}
            >
              Non Veg
            </button>
          </div>
        </Container>
        <MenuBody filteredFoodItems={filteredFoodItems} />
      </Container>

      <SubFooter
        to={"/cart"}
        desc={"Go to cart to complete your order"}
        button={"Go to Cart"}
      />
    </div>
  );
};

export default Menu;
