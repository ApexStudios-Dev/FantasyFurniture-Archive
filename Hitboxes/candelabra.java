Stream.of(
Block.box(6.5, 0, 6.5, 9.5, 2, 9.5),
Block.box(7, 2, 7, 9, 5, 9),
Block.box(1.25, 5, 6.5, 14.75, 12, 9.5),
Block.box(12.25, 12, 7, 14.25, 15, 9),
Block.box(1.75, 12, 7, 3.75, 15, 9),
Block.box(7, 12, 7, 9, 16, 9)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();